package top.mc_plfd_host.Algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Sort {
    
    // Bubble Sort - O(n²)
    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }
    }
    
    // Selection Sort - O(n²)
    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }
    
    // Insertion Sort - O(n²)
    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
    
    // Merge Sort - O(n log n)
    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        if (array.length < 2) {
            return;
        }
        mergeSort(array, 0, array.length - 1);
    }
    
    private static <T extends Comparable<T>> void mergeSort(T[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }
    
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge(T[] array, int left, int mid, int right) {
        T[] temp = (T[]) new Comparable[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (array[i].compareTo(array[j]) <= 0) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }
        
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        
        while (j <= right) {
            temp[k++] = array[j++];
        }
        
        System.arraycopy(temp, 0, array, left, temp.length);
    }
    
    // Quick Sort - O(n log n) average, O(n²) worst
    public static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }
    
    private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }
    
    private static <T extends Comparable<T>> int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }
    
    // Quick Sort for int[]
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }
    
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }
    
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }
    
    // Quick Sort for double[]
    public static void quickSort(double[] array) {
        quickSort(array, 0, array.length - 1);
    }
    
    private static void quickSort(double[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }
    
    private static int partition(double[] array, int low, int high) {
        double pivot = array[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }
    
    // Heap Sort - O(n log n)
    public static <T extends Comparable<T>> void heapSort(T[] array) {
        int n = array.length;
        
        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        
        // Extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }
    
    private static <T extends Comparable<T>> void heapify(T[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }
        
        if (right < n && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }
        
        if (largest != i) {
            swap(array, i, largest);
            heapify(array, n, largest);
        }
    }
    
    // Counting Sort - O(n + k) for integers
    public static void countingSort(int[] array) {
        if (array.length == 0) return;
        
        int max = Arrays.stream(array).max().getAsInt();
        int min = Arrays.stream(array).min().getAsInt();
        int range = max - min + 1;
        
        int[] count = new int[range];
        int[] output = new int[array.length];
        
        // Count frequencies
        for (int num : array) {
            count[num - min]++;
        }
        
        // Calculate cumulative count
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        
        // Build output array
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
        }
        
        System.arraycopy(output, 0, array, 0, array.length);
    }
    
    // Radix Sort - O(d * (n + k)) for integers
    public static void radixSort(int[] array) {
        if (array.length == 0) return;
        
        int max = Arrays.stream(array).max().getAsInt();
        
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp);
        }
    }
    
    private static void countingSortByDigit(int[] array, int exp) {
        int[] output = new int[array.length];
        int[] count = new int[10];
        
        // Count frequencies
        for (int num : array) {
            count[(num / exp) % 10]++;
        }
        
        // Calculate cumulative count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        
        // Build output array
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }
        
        System.arraycopy(output, 0, array, 0, array.length);
    }
    
    // Bucket Sort - O(n + k) average case
    public static void bucketSort(float[] array) {
        if (array.length == 0) return;
        
        int n = array.length;
        List<Float>[] buckets = new ArrayList[n];
        
        // Create buckets
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }
        
        // Distribute elements into buckets
        for (float num : array) {
            int bucketIndex = (int) (n * num);
            if (bucketIndex == n) bucketIndex = n - 1;
            buckets[bucketIndex].add(num);
        }
        
        // Sort individual buckets and concatenate
        int index = 0;
        for (int i = 0; i < n; i++) {
            buckets[i].sort(null);
            for (float num : buckets[i]) {
                array[index++] = num;
            }
        }
    }
    
    // Shell Sort - O(n^(3/2)) average
    public static <T extends Comparable<T>> void shellSort(T[] array) {
        int n = array.length;
        
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                T temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap].compareTo(temp) > 0; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }
    
    // Tim Sort (Java's default for objects) - O(n log n)
    public static <T extends Comparable<T>> void timSort(T[] array) {
        Arrays.sort(array);
    }
    
    // Generic sort with custom comparator
    public static <T> void sort(T[] array, Comparator<? super T> comparator) {
        Arrays.sort(array, comparator);
    }
    
    // Utility method to swap elements
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    // Utility method to swap elements in int array
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    // Utility method to swap elements in double array
    private static void swap(double[] array, int i, int j) {
        double temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    // Check if array is sorted
    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
    
    // Get sorting statistics
    public static <T extends Comparable<T>> SortingStats getSortingStats(T[] array, String algorithmName) {
        T[] copy = Arrays.copyOf(array, array.length);
        long startTime = System.nanoTime();
        
        switch (algorithmName.toLowerCase()) {
            case "bubble":
                bubbleSort(copy);
                break;
            case "selection":
                selectionSort(copy);
                break;
            case "insertion":
                insertionSort(copy);
                break;
            case "merge":
                mergeSort(copy);
                break;
            case "quick":
                quickSort(copy);
                break;
            case "heap":
                heapSort(copy);
                break;
            case "shell":
                shellSort(copy);
                break;
            case "tim":
                timSort(copy);
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithmName);
        }
        
        long endTime = System.nanoTime();
        return new SortingStats(algorithmName, array.length, endTime - startTime);
    }
    
    public static class SortingStats {
        private final String algorithm;
        private final int arraySize;
        private final long timeNanos;
        
        public SortingStats(String algorithm, int arraySize, long timeNanos) {
            this.algorithm = algorithm;
            this.arraySize = arraySize;
            this.timeNanos = timeNanos;
        }
        
        public String getAlgorithm() { return algorithm; }
        public int getArraySize() { return arraySize; }
        public long getTimeNanos() { return timeNanos; }
        public double getTimeMillis() { return timeNanos / 1_000_000.0; }
        
        @Override
        public String toString() {
            return String.format("%s: %d elements, %.3f ms", 
                algorithm, arraySize, getTimeMillis());
        }
    }
}
