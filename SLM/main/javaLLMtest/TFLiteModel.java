import org.tensorflow.lite.Interpreter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class TFLiteModel {
    private Interpreter interpreter;

    public TFLiteModel(String modelPath) {
        try {
            interpreter = new Interpreter(new java.io.File(modelPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float[] predict(float[] inputData) {
        float[][] outputData = new float[1][3]; // Adjust size according to your model's output
        ByteBuffer inputBuffer = ByteBuffer.allocateDirect(inputData.length * 4);
        inputBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer inputFloatBuffer = inputBuffer.asFloatBuffer();
        inputFloatBuffer.put(inputData);

        interpreter.run(inputBuffer, outputData);
        return outputData[0];
    }

    public static void main(String[] args) {
        TFLiteModel model = new TFLiteModel("model.tflite");
        float[] inputData = {1.0f, 2.0f, 3.0f};
        float[] outputData = model.predict(inputData);
        for (float value : outputData) {
            System.out.println(value);
        }
    }
}


