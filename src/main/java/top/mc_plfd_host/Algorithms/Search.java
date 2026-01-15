package top.mc_plfd_host.Algorithms;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Search {
    
    // Linear Search - O(n)
    public static <T> int linearSearch(T[] array, T target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    // Linear Search with comparator - O(n)
    public static <T> int linearSearch(T[] array, T target, Comparator<? super T> comparator) {
        for (int i = 0; i < array.length; i++) {
            if (comparator.compare(array[i], target) == 0) {
                return i;
            }
        }
        return -1;
    }
    
    // Binary Search - O(log n) - array must be sorted
    public static <T extends Comparable<T>> int binarySearch(T[] array, T target) {
        return binarySearch(array, target, 0, array.length - 1);
    }
    
    private static <T extends Comparable<T>> int binarySearch(T[] array, T target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = array[mid].compareTo(target);
            
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    // Binary Search with comparator - O(log n)
    public static <T> int binarySearch(T[] array, T target, Comparator<? super T> comparator) {
        return binarySearch(array, target, comparator, 0, array.length - 1);
    }
    
    private static <T> int binarySearch(T[] array, T target, Comparator<? super T> comparator, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = comparator.compare(array[mid], target);
            
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    // Binary Search for primitive arrays
    public static int binarySearch(int[] array, int target) {
        return binarySearch(array, target, 0, array.length - 1);
    }
    
    private static int binarySearch(int[] array, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    public static int binarySearch(double[] array, double target) {
        return binarySearch(array, target, 0, array.length - 1);
    }
    
    private static int binarySearch(double[] array, double target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (Math.abs(array[mid] - target) < 1e-10) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    // Jump Search - O(√n) - array must be sorted
    public static <T extends Comparable<T>> int jumpSearch(T[] array, T target) {
        int n = array.length;
        int step = (int) Math.sqrt(n);
        int prev = 0;
        
        // Find the block where element could be present
        while (array[Math.min(step, n) - 1].compareTo(target) < 0) {
            prev = step;
            step += (int) Math.sqrt(n);
            if (prev >= n) {
                return -1;
            }
        }
        
        // Linear search in the identified block
        while (array[prev].compareTo(target) < 0) {
            prev++;
            if (prev == Math.min(step, n)) {
                return -1;
            }
        }
        
        return array[prev].equals(target) ? prev : -1;
    }
    
    // Interpolation Search - O(log log n) average - array must be sorted and uniformly distributed
    public static int interpolationSearch(int[] array, int target) {
        int low = 0, high = array.length - 1;
        
        while (low <= high && target >= array[low] && target <= array[high]) {
            if (low == high) {
                return array[low] == target ? low : -1;
            }
            
            // Calculate the probing position
            int pos = low + ((target - array[low]) * (high - low)) / (array[high] - array[low]);
            
            if (array[pos] == target) {
                return pos;
            } else if (array[pos] < target) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        
        return -1;
    }
    
    // Exponential Search - O(log n) - array must be sorted
    public static <T extends Comparable<T>> int exponentialSearch(T[] array, T target) {
        if (array.length == 0) {
            return -1;
        }
        
        // If target is at first position
        if (array[0].equals(target)) {
            return 0;
        }
        
        // Find range for binary search
        int i = 1;
        while (i < array.length && array[i].compareTo(target) <= 0) {
            i = i * 2;
        }
        
        // Binary search in found range
        return binarySearch(array, target, i / 2, Math.min(i, array.length - 1));
    }
    
    // Ternary Search - O(log n) - array must be sorted
    public static <T extends Comparable<T>> int ternarySearch(T[] array, T target) {
        return ternarySearch(array, target, 0, array.length - 1);
    }
    
    private static <T extends Comparable<T>> int ternarySearch(T[] array, T target, int left, int right) {
        while (left <= right) {
            int third = (right - left) / 3;
            int mid1 = left + third;
            int mid2 = right - third;
            
            if (array[mid1].equals(target)) {
                return mid1;
            }
            if (array[mid2].equals(target)) {
                return mid2;
            }
            
            if (target.compareTo(array[mid1]) < 0) {
                right = mid1 - 1;
            } else if (target.compareTo(array[mid2]) > 0) {
                left = mid2 + 1;
            } else {
                left = mid1 + 1;
                right = mid2 - 1;
            }
        }
        
        return -1;
    }
    
    // Find all occurrences of target
    public static <T> List<Integer> findAllOccurrences(T[] array, T target) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                indices.add(i);
            }
        }
        return indices;
    }
    
    // Find all occurrences in sorted array (efficient)
    public static <T extends Comparable<T>> List<Integer> findAllOccurrencesSorted(T[] array, T target) {
        List<Integer> indices = new ArrayList<>();
        
        // Find first occurrence
        int first = findFirstOccurrence(array, target);
        if (first == -1) {
            return indices;
        }
        
        // Find last occurrence
        int last = findLastOccurrence(array, target);
        
        // Add all indices from first to last
        for (int i = first; i <= last; i++) {
            indices.add(i);
        }
        
        return indices;
    }
    
    private static <T extends Comparable<T>> int findFirstOccurrence(T[] array, T target) {
        int left = 0, right = array.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = array[mid].compareTo(target);
            
            if (cmp == 0) {
                result = mid;
                right = mid - 1; // Continue searching left side
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    private static <T extends Comparable<T>> int findLastOccurrence(T[] array, T target) {
        int left = 0, right = array.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = array[mid].compareTo(target);
            
            if (cmp == 0) {
                result = mid;
                left = mid + 1; // Continue searching right side
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    // Find Kth smallest element (Quickselect) - O(n) average
    public static <T extends Comparable<T>> T kthSmallest(T[] array, int k) {
        if (k < 0 || k >= array.length) {
            throw new IllegalArgumentException("k is out of bounds");
        }
        return quickselect(array, 0, array.length - 1, k);
    }
    
    private static <T extends Comparable<T>> T quickselect(T[] array, int left, int right, int k) {
        if (left == right) {
            return array[left];
        }
        
        int pivotIndex = partition(array, left, right);
        
        if (k == pivotIndex) {
            return array[k];
        } else if (k < pivotIndex) {
            return quickselect(array, left, pivotIndex - 1, k);
        } else {
            return quickselect(array, pivotIndex + 1, right, k);
        }
    }
    
    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        T pivot = array[right];
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, right);
        return i;
    }
    
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    // Search in rotated sorted array
    public static int searchInRotated(int[] array, int target) {
        int left = 0, right = array.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] == target) {
                return mid;
            }
            
            // Check if left half is sorted
            if (array[left] <= array[mid]) {
                if (array[left] <= target && target < array[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (array[mid] < target && target <= array[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    // Find peak element in array
    public static int findPeak(int[] array) {
        return findPeak(array, 0, array.length - 1);
    }
    
    private static int findPeak(int[] array, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] > array[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    // Search performance comparison
    public static <T extends Comparable<T>> SearchStats compareSearchAlgorithms(T[] array, T target) {
        T[] sortedArray = array.clone();
        Sort.quickSort(sortedArray);
        
        long startTime, endTime;
        
        // Linear Search
        startTime = System.nanoTime();
        int linearResult = linearSearch(sortedArray, target);
        endTime = System.nanoTime();
        long linearTime = endTime - startTime;
        
        // Binary Search
        startTime = System.nanoTime();
        int binaryResult = binarySearch(sortedArray, target);
        endTime = System.nanoTime();
        long binaryTime = endTime - startTime;
        
        // Jump Search
        startTime = System.nanoTime();
        int jumpResult = jumpSearch(sortedArray, target);
        endTime = System.nanoTime();
        long jumpTime = endTime - startTime;
        
        return new SearchStats(linearResult, binaryResult, jumpResult, linearTime, binaryTime, jumpTime);
    }
    
    public static class SearchStats {
        private final int linearResult;
        private final int binaryResult;
        private final int jumpResult;
        private final long linearTimeNanos;
        private final long binaryTimeNanos;
        private final long jumpTimeNanos;
        
        public SearchStats(int linearResult, int binaryResult, int jumpResult, 
                          long linearTimeNanos, long binaryTimeNanos, long jumpTimeNanos) {
            this.linearResult = linearResult;
            this.binaryResult = binaryResult;
            this.jumpResult = jumpResult;
            this.linearTimeNanos = linearTimeNanos;
            this.binaryTimeNanos = binaryTimeNanos;
            this.jumpTimeNanos = jumpTimeNanos;
        }
        
        public int getLinearResult() { return linearResult; }
        public int getBinaryResult() { return binaryResult; }
        public int getJumpResult() { return jumpResult; }
        public long getLinearTimeNanos() { return linearTimeNanos; }
        public long getBinaryTimeNanos() { return binaryTimeNanos; }
        public long getJumpTimeNanos() { return jumpTimeNanos; }
        
        @Override
        public String toString() {
            return String.format(
                "Linear: %d (%.3f ms), Binary: %d (%.3f ms), Jump: %d (%.3f ms)",
                linearResult, linearTimeNanos / 1_000_000.0,
                binaryResult, binaryTimeNanos / 1_000_000.0,
                jumpResult, jumpTimeNanos / 1_000_000.0
            );
        }
    }
}
