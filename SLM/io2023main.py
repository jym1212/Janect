  Preparing metadata (setup.py) ... done
     ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 6.0/6.0 MB 9.0 MB/s eta 0:00:00
     ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 585.9/585.9 MB 2.7 MB/s eta 0:00:00
     ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 1.7/1.7 MB 77.1 MB/s eta 0:00:00
     ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 17.3/17.3 MB 84.0 MB/s eta 0:00:00
     ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 5.6/5.6 MB 101.3 MB/s eta 0:00:00
     ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 440.7/440.7 kB 46.4 MB/s eta 0:00:00
     ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 1.7/1.7 MB 84.5 MB/s eta 0:00:00
     ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 1.7/1.7 MB 89.2 MB/s eta 0:00:00
  Building wheel for keras-nlp (setup.py) ... done
ERROR: pip's dependency resolver does not currently take into account all the packages that are installed. This behaviour is the source of the following dependency conflicts.
torch 2.2.1+cu121 requires nvidia-cublas-cu12==12.1.3.1; platform_system == "Linux" and platform_machine == "x86_64", which is not installed.
torch 2.2.1+cu121 requires nvidia-cuda-cupti-cu12==12.1.105; platform_system == "Linux" and platform_machine == "x86_64", which is not installed.
torch 2.2.1+cu121 requires nvidia-cuda-nvrtc-cu12==12.1.105; platform_system == "Linux" and platform_machine == "x86_64", which is not installed.
torch 2.2.1+cu121 requires nvidia-cuda-runtime-cu12==12.1.105; platform_system == "Linux" and platform_machine == "x86_64", which is not installed.
torch 2.2.1+cu121 requires nvidia-cudnn-cu12==8.9.2.26; platform_system == "Linux" and platform_machine == "x86_64", which is not installed.
torch 2.2.1+cu121 requires nvidia-cufft-cu12==11.0.2.54; platform_system == "Linux" and platform_machine == "x86_64", which is not installed.
torch 2.2.1+cu121 requires nvidia-curand-cu12==10.3.2.106; platform_system == "Linux" and platform_machine == "x86_64", which is not installed.
torch 2.2.1+cu121 requires nvidia-cusolver-cu12==11.4.5.107; platform_system == "Linux" and platform_machine == "x86_64", which is not installed.
torch 2.2.1+cu121 requires nvidia-cusparse-cu12==12.1.0.106; platform_system == "Linux" and platform_machine == "x86_64", which is not installed.
torch 2.2.1+cu121 requires nvidia-nccl-cu12==2.19.3; platform_system == "Linux" and platform_machine == "x86_64", which is not installed.
torch 2.2.1+cu121 requires nvidia-nvtx-cu12==12.1.105; platform_system == "Linux" and platform_machine == "x86_64", which is not installed.
sqlalchemy 2.0.30 requires typing-extensions>=4.6.0, but you have typing-extensions 4.5.0 which is incompatible.
pandas-stubs 2.0.3.230814 requires numpy>=1.25.0; python_version >= "3.9", but you have numpy 1.24.3 which is incompatible.
pydantic 2.7.1 requires typing-extensions>=4.6.1, but you have typing-extensions 4.5.0 which is incompatible.
pydantic-core 2.18.2 requires typing-extensions!=4.7.0,>=4.6.0, but you have typing-extensions 4.5.0 which is incompatible.
torch 2.2.1+cu121 requires typing-extensions>=4.8.0, but you have typing-extensions 4.5.0 which is incompatible.
Downloading data from https://storage.googleapis.com/keras-nlp/models/gpt2_base_en/v1/vocab.json
1042301/1042301 [==============================] - 1s 1us/step
Downloading data from https://storage.googleapis.com/keras-nlp/models/gpt2_base_en/v1/merges.txt
456318/456318 [==============================] - 1s 1us/step
Downloading data from https://storage.googleapis.com/keras-nlp/models/gpt2_base_en/v1/model.h5
497986112/497986112 [==============================] - 22s 0us/step
WARNING:tensorflow:The following Variables were used in a Lambda layer's call (tf.linalg.matmul), but are not present in its tracked objects:   <tf.Variable 'token_embedding/embeddings:0' shape=(50257, 768) dtype=float32>. This is a strong indication that the Lambda layer should be rewritten as a subclassed Layer.

GPT-2 output:
My trip to Yosemite was a whirlwind of excitement and adventure. We were able to take a short drive to our destination, which was the famous Yosemite Valley, which has an abundance of natural beauty. The view from the top of the valley and the view of the Grand Canyon is amazing. The view was also very scenic. We enjoyed our time here and were able to take a few more photos and share with you the photos that we took.

As always, we will be posting more of our Yosemite photos as they are posted.
TOTAL TIME ELAPSED:  20.758960962295532
WARNING:tensorflow:Skipping full serialization of Keras layer <keras_nlp.models.gpt2.gpt2_causal_lm_preprocessor.GPT2CausalLMPreprocessor object at 0x7e0864badc60>, because it is not built.
WARNING:absl:Found untraced functions such as gpt2_tokenizer_1_layer_call_fn, gpt2_tokenizer_1_layer_call_and_return_conditional_losses, cached_multi_head_attention_layer_call_fn, cached_multi_head_attention_layer_call_and_return_conditional_losses, layer_normalization_layer_call_fn while saving (showing 5 of 314). These functions will not be directly callable after loading.
---------------------------------------------------------------------------
NameError                                 Traceback (most recent call last)
<ipython-input-1-4926dbd8024f> in <cell line: 58>()
     58 try:
     59     generate_tflite = converter.convert()
---> 60     run_inference("I'm enjoying a", generate_tflite)
     61 
     62     # Save the unquantized model

<ipython-input-1-4926dbd8024f> in run_inference(input, generate_tflite)
     37     interp = interpreter.InterpreterWithCustomOps(
     38         model_content=generate_tflite,
---> 39         custom_op_registerers=tf_text.tflite_registrar.SELECT_TFTEXT_OPS)
     40     interp.get_signature_list()
     41 

NameError: name 'tf_text' is not defined