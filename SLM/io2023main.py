

!pip install -q git+https://github.com/keras-team/keras-nlp.git@google-io-2023 tensorflow-text==2.12

import numpy as np
import keras_nlp
import tensorflow as tf
import tensorflow_datasets as tfds
import tensorflow_text as tf_text
from tensorflow import keras
from tensorflow.lite.python import interpreter
import time

gpt2_tokenizer = keras_nlp.models.GPT2Tokenizer.from_preset("gpt2_base_en")
gpt2_preprocessor = keras_nlp.models.GPT2CausalLMPreprocessor.from_preset(
    "gpt2_base_en",
    sequence_length=256,
    add_end_token=True,
)
gpt2_lm = keras_nlp.models.GPT2CausalLM.from_preset("gpt2_base_en", preprocessor=gpt2_preprocessor)

start = time.time()

output = gpt2_lm.generate("My trip to Yosemite was", max_length=200)
print("\nGPT-2 output:")
print(output.numpy().decode("utf-8"))

end = time.time()
print("TOTAL TIME ELAPSED: ", end - start)

start = time.time()

output = gpt2_lm.generate("That Italian restaurant is", max_length=200)
print("\nGPT-2 output:")
print(output.numpy().decode("utf-8"))

end = time.time()
print("TOTAL TIME ELAPSED: ", end - start)

gpt2_tokenizer.tokenize(["Today is a beautiful day"]).flat_values

ds = tf.data.Dataset.from_tensor_slices(["Today is a beautiful day"])
preprocessed_ds = ds.map(gpt2_preprocessor)
output = next(iter(preprocessed_ds))
print('token ids:')
print(output[0]['token_ids'])
print('padding masks:')
print(output[0]['padding_mask'])

prediction_logits = gpt2_lm.predict(["Today is a beautiful day"])
print(prediction_logits.shape)
print(prediction_logits[0])

start = time.time()

cnn_ds = tfds.load('cnn_dailymail', as_supervised=True)

end = time.time()
print("TOTAL TIME ELAPSED: ", end - start)

for article, highlights in cnn_ds['train']:
  print(article.numpy())
  print(highlights.numpy())
  break

from nltk import tokenize
import nltk

nltk.download('punkt')

def merge_sentences(sentences, max_length):
    res = []
    cur_len = 0
    cur_sentences = []
    for s in sentences:
        if cur_len + len(s) > max_length:
            # If adding the next sentence exceeds `max_length`, we add the
            # current sentences into collection
            res.append(" ".join(cur_sentences))
            cur_len = len(s)
            cur_sentences = [s]
        else:
            cur_len += len(s)
            cur_sentences.append(s)
    res.append(" ".join(cur_sentences))
    return res

import progressbar

max_length = 512
all_sentences = []
count = 0
total = len(cnn_ds["train"])
num_articles_to_process = 20000
progressbar_update_freq = 2000

widgets = [' [',
         progressbar.Timer(format= 'elapsed time: %(elapsed)s'),
         '] ',
           progressbar.Bar('*'),' (',
           progressbar.ETA(), ') ',
          ]

# Render a progressbar to track progress
bar = progressbar.ProgressBar(
    max_value=num_articles_to_process // progressbar_update_freq + 2,
    widgets=widgets).start()

for article, highlight in cnn_ds['train']:
  # Use NLTK tokenize to split articles into sentences
  sentences = tokenize.sent_tokenize(str(article))
  # Merge individual sentences into longer context
  combined_res = merge_sentences(sentences, max_length)
  # Add merged context into collection
  all_sentences.extend(combined_res)
  count += 1
  if count % progressbar_update_freq == 0:
    bar.update(count / progressbar_update_freq)
  if count >= num_articles_to_process:
    break

tf_train_ds = tf.data.Dataset.from_tensor_slices(all_sentences)
processed_ds = tf_train_ds.map(gpt2_preprocessor, tf.data.AUTOTUNE).batch(20).cache().prefetch(tf.data.AUTOTUNE)
part_of_ds = processed_ds.take(100)

gpt2_lm.include_preprocessing = False

num_epochs = 1

lr = tf.keras.optimizers.schedules.PolynomialDecay(
    5e-5,
    decay_steps=part_of_ds.cardinality() * num_epochs,
    end_learning_rate=0.0,
)
loss = tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True)
gpt2_lm.compile(
    optimizer=keras.optimizers.experimental.Adam(lr),
    loss=loss,
    weighted_metrics=["accuracy"])

gpt2_lm.fit(part_of_ds, epochs=num_epochs)

start = time.time()

output = gpt2_lm.generate("Breaking news: the river", max_length=200)
print("\nGPT-2 output:")
print(output.numpy().decode("utf-8"))

end = time.time()
print("TOTAL TIME ELAPSED: ", end - start)

gpt2_lm.backbone.save_weights("finetuned_model.h5")

del gpt2_tokenizer, gpt2_preprocessor, gpt2_lm

gpt2_lm = keras_nlp.models.GPT2CausalLM.from_preset("gpt2_base_en_cnn_dailymail")
# Alternative model: finetuned on Reddit dataset
# gpt2_lm = keras_nlp.models.GPT2CausalLM.from_preset("gpt2_base_en_reddit")
gpt2_lm.generate(
    ["Breaking news: the river"],
    max_length=200,
)

@tf.function
def generate(prompt, max_length):
    return gpt2_lm.generate(prompt, max_length)

concrete_func = generate.get_concrete_function(tf.TensorSpec([], tf.string), 100)

def run_inference(input, generate_tflite):
  interp = interpreter.InterpreterWithCustomOps(
      model_content=generate_tflite,
      custom_op_registerers=tf_text.tflite_registrar.SELECT_TFTEXT_OPS)
  interp.get_signature_list()

  generator = interp.get_signature_runner('serving_default')
  output = generator(prompt=np.array([input]))
  print("\nGenerated with TFLite:\n", output["output_0"])

gpt2_lm.jit_compile = False
converter = tf.lite.TFLiteConverter.from_concrete_functions([concrete_func],
                                                            gpt2_lm)
converter.target_spec.supported_ops = [
  tf.lite.OpsSet.TFLITE_BUILTINS, # enable TensorFlow Lite ops.
  tf.lite.OpsSet.SELECT_TF_OPS # enable TensorFlow ops.
]
converter.allow_custom_ops = True
converter.target_spec.experimental_select_user_tf_ops = ["UnsortedSegmentJoin", "UpperBound"]
converter._experimental_guarantee_all_funcs_one_use = True
generate_tflite = converter.convert()
run_inference("I'm enjoying a", generate_tflite)

with open('unquantized_gpt2.tflite', 'wb') as f:
  f.write(generate_tflite)

!ls -lh *.tflite

"""There are several flavors of quantizations implemented by TensorFlow Lite. You can visit the [TFLite Model optimization page](https://www.tensorflow.org/lite/performance/model_optimization) and [TensorFlow Model Optimization Toolkit page](https://www.tensorflow.org/model_optimization) to learn more.

In this workshop you will use the [post-training dynamic range quantization](https://www.tensorflow.org/lite/performance/post_training_quant) by setting the converter optimization flag to 'tf.lite.Optimize.DEFAULT'. The rest of the conversion process is the same as before.
"""

gpt2_lm.jit_compile = False
converter = tf.lite.TFLiteConverter.from_concrete_functions([concrete_func],
                                                            gpt2_lm)
converter.target_spec.supported_ops = [
  tf.lite.OpsSet.TFLITE_BUILTINS, # enable TensorFlow Lite ops.
  tf.lite.OpsSet.SELECT_TF_OPS # enable TensorFlow ops.
]
converter.allow_custom_ops = True
converter.optimizations = [tf.lite.Optimize.DEFAULT]
converter.target_spec.experimental_select_user_tf_ops = ["UnsortedSegmentJoin", "UpperBound"]
converter._experimental_guarantee_all_funcs_one_use = True
quant_generate_tflite = converter.convert()
run_inference("I'm enjoying a", quant_generate_tflite)

with open('quantized_gpt2.tflite', 'wb') as f:
  f.write(quant_generate_tflite)

!ls -lh *.tflite

!mv quantized_gpt2.tflite autocomplete.tflite

with open('autocomplete.tflite', 'rb') as file:
    model = file.read()

def run_inference(input):
  interp = interpreter.InterpreterWithCustomOps(
      model_content=model,
      custom_op_registerers=tf_text.tflite_registrar.SELECT_TFTEXT_OPS)
  interp.get_signature_list()

  generator = interp.get_signature_runner('serving_default')
  output = generator(prompt=np.array([input]))
  output_text = output["output_0"].item(0).decode('utf-8')
  return output_text

!pip install visualblocks
import visualblocks

server = visualblocks.Server(text_to_text=run_inference)

server.display()

