import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DatasetGenerator {
    public static void main(String[] args) {
        int[] sizes = {32000, 64000, 128000};
        Random random = new Random();

        for (int size : sizes) {
            try (FileWriter writer = new FileWriter("dataset_" + size + ".txt")) {
                for (int i = 0; i < size; i++) {
                    writer.write(random.nextInt(100000) + "\n");
                }
                System.out.println("Generated dataset_" + size + ".txt");
            } catch (IOException e) {
                System.err.println("Error writing file: " + e.getMessage());
            }
        }
    }
}
