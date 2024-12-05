import java.io.*;
import java.util.*;

public class MergeSortOutput {
    public static void main(String[] args) throws IOException {
        String[] files = {"dataset_10.txt", "dataset_2000.txt", "dataset_4000.txt", "dataset_16000.txt"};

        for (String file : files) {
            List<Integer> numbers = readFile(file);
            int[] array = numbers.stream().mapToInt(i -> i).toArray();

            Stopwatch timer = new Stopwatch();
            mergeSort(array, 0, array.length - 1);
            double time = timer.elapsedTime();

            System.out.println("File: " + file + ", Time: " + time + " seconds");

            // Write the sorted array to a new file
            String outputFileName = file.replace(".txt", "_sorted.txt");
            writeFile(array, outputFileName);
            System.out.println("Sorted results saved to: " + outputFileName);
        }
    }


    private static List<Integer> readFile(String fileName) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        }
        return numbers;
    }


    private static void writeFile(int[] array, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        }
    }


    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = array[left + i];
        for (int j = 0; j < n2; j++) R[j] = array[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k++] = L[i++];
            } else {
                array[k++] = R[j++];
            }
        }

        while (i < n1) array[k++] = L[i++];
        while (j < n2) array[k++] = R[j++];
    }
}
