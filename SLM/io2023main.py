!pip install -q git+https://github.com/keras-team/keras-nlp.git@google-io-2023 tensorflow-text==2.12

import keras_nlp
import tensorflow as tf
from tensorflow.lite.python import interpreter
import numpy as np
import time

# Load and prepare the model
gpt2_tokenizer = keras_nlp.models.GPT2Tokenizer.from_preset("gpt2_base_en")
gpt2_preprocessor = keras_nlp.models.GPT2CausalLMPreprocessor.from_preset(
    "gpt2_base_en",
    sequence_length=256,
    add_end_token=True,
)
gpt2_lm = keras_nlp.models.GPT2CausalLM.from_preset("gpt2_base_en", preprocessor=gpt2_preprocessor)

# Ensure the model is built
gpt2_lm.build(input_shape=(None, 256))

# Generate text with the model
start = time.time()
output = gpt2_lm.generate("My trip to Yosemite was", max_length=200)
print("\nGPT-2 output:")
print(output.numpy().decode("utf-8"))
end = time.time()
print("TOTAL TIME ELAPSED: ", end - start)

# Define function to convert and run inference with TFLite model
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

# Convert to TFLite format
gpt2_lm.jit_compile = False
converter = tf.lite.TFLiteConverter.from_concrete_functions([concrete_func], gpt2_lm)
converter.target_spec.supported_ops = [
    tf.lite.OpsSet.TFLITE_BUILTINS, # enable TensorFlow Lite ops
    tf.lite.OpsSet.SELECT_TF_OPS    # enable TensorFlow ops
]
converter.allow_custom_ops = True
converter.target_spec.experimental_select_user_tf_ops = ["UnsortedSegmentJoin", "UpperBound"]
converter._experimental_guarantee_all_funcs_one_use = True

# Attempt conversion and handle potential errors
try:
    generate_tflite = converter.convert()
    run_inference("I'm enjoying a", generate_tflite)

    # Save the unquantized model
    with open('unquantized_gpt2.tflite', 'wb') as f:
        f.write(generate_tflite)

    # Perform quantization
    converter.optimizations = [tf.lite.Optimize.DEFAULT]
    quant_generate_tflite = converter.convert()
    run_inference("I'm enjoying a", quant_generate_tflite)

    # Save the quantized model
    with open('quantized_gpt2.tflite', 'wb') as f:
        f.write(quant_generate_tflite)

    !mv quantized_gpt2.tflite autocomplete.tflite

    # Load and run the quantized model
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

    # Install and set up Visual Blocks
    !pip install visualblocks
    import visualblocks

    server = visualblocks.Server(text_to_text=run_inference)
    server.display()

except AttributeError as e:
    print(f"Error during conversion: {e}")
