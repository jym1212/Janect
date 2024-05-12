#https://www.tensorflow.org/lite/inference_with_metadata/codegen?hl=ko&_gl=1*a0agwu*_up*MQ..*_ga*MTUxNTI4MjU0MS4xNzE1NTIzNjgz*_ga_W0YLR4190T*MTcxNTUyMzY4Mi4xLjAuMTcxNTUyMzY4Ny4wLjAuMA..#java

pip install tflite-support
tflite_codegen --model=./model_with_metadata/mobilenet_v1_0.75_160_quantized.tflite \
    --package_name=org.tensorflow.lite.classify \
    --model_class_name=MyClassifierModel \
    --destination=./classify_wrapper
# Zip up the generated code
!zip -r classify_wrapper.zip classify_wrapper/

# Download the archive
from google.colab import files
files.download('classify_wrapper.zip')