package top.mc_plfd_host.Algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class MathUtils {
    
    // Greatest Common Divisor (GCD) - Euclidean Algorithm
    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    // GCD for multiple numbers
    public static long gcd(long... numbers) {
        if (numbers.length == 0) return 0;
        long result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = gcd(result, numbers[i]);
            if (result == 1) break; // Can't get smaller than 1
        }
        return result;
    }
    
    // Least Common Multiple (LCM)
    public static long lcm(long a, long b) {
        return Math.abs(a * b) / gcd(a, b);
    }
    
    // LCM for multiple numbers
    public static long lcm(long... numbers) {
        if (numbers.length == 0) return 0;
        long result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = lcm(result, numbers[i]);
        }
        return result;
    }
    
    // Power function with exponentiation by squaring - O(log n)
    public static long power(long base, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent must be non-negative");
        }
        if (exponent == 0) return 1;
        if (exponent == 1) return base;
        
        long result = 1;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result *= base;
            }
            base *= base;
            exponent >>= 1;
        }
        return result;
    }
    
    // Modular exponentiation - O(log n)
    public static long modPower(long base, long exponent, long modulus) {
        if (modulus == 1) return 0;
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent must be non-negative");
        }
        
        long result = 1;
        base = base % modulus;
        
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exponent >>= 1;
        }
        
        return result;
    }
    
    // Check if number is prime - O(√n)
    public static boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    
    // Sieve of Eratosthenes - Generate all primes up to n - O(n log log n)
    public static boolean[] sieveOfEratosthenes(int n) {
        if (n < 2) {
            return new boolean[n + 1];
        }
        
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        
        return isPrime;
    }
    
    // Get list of primes up to n
    public static List<Integer> getPrimes(int n) {
        boolean[] isPrime = sieveOfEratosthenes(n);
        List<Integer> primes = new ArrayList<>();
        
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        
        return primes;
    }
    
    // Prime factorization - O(√n)
    public static List<Long> primeFactorization(long n) {
        List<Long> factors = new ArrayList<>();
        
        // Handle 2s
        while (n % 2 == 0) {
            factors.add(2L);
            n /= 2;
        }
        
        // Handle odd numbers
        for (long i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        
        // If remaining n is a prime > 2
        if (n > 2) {
            factors.add(n);
        }
        
        return factors;
    }
    
    // Fibonacci number - O(log n) using matrix exponentiation
    public static long fibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        
        long[][] result = {{1, 1}, {1, 0}};
        matrixPower(result, n - 1);
        
        return result[0][0];
    }
    
    private static void matrixPower(long[][] matrix, int power) {
        if (power <= 1) return;
        
        long[][] temp = {{1, 1}, {1, 0}};
        matrixPower(matrix, power / 2);
        matrixMultiply(matrix, matrix);
        
        if (power % 2 != 0) {
            matrixMultiply(matrix, temp);
        }
    }
    
    private static void matrixMultiply(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        
        for (int i = 0; i < 2; i++) {
            System.arraycopy(result[i], 0, a[i], 0, 2);
        }
    }
    
    // Fibonacci sequence up to n terms
    public static long[] fibonacciSequence(int n) {
        if (n <= 0) return new long[0];
        if (n == 1) return new long[]{0};
        
        long[] sequence = new long[n];
        sequence[0] = 0;
        sequence[1] = 1;
        
        for (int i = 2; i < n; i++) {
            sequence[i] = sequence[i - 1] + sequence[i - 2];
        }
        
        return sequence;
    }
    
    // Calculate factorial - O(n)
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n > 20) {
            throw new IllegalArgumentException("Factorial too large for long type");
        }
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    // Calculate nCr (combinations) - O(min(r, n-r))
    public static long combination(int n, int r) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;
        
        r = Math.min(r, n - r); // Take advantage of symmetry
        
        long result = 1;
        for (int i = 0; i < r; i++) {
            result = result * (n - i) / (i + 1);
        }
        
        return result;
    }
    
    // Calculate nPr (permutations) - O(r)
    public static long permutation(int n, int r) {
        if (r < 0 || r > n) return 0;
        if (r == 0) return 1;
        
        long result = 1;
        for (int i = 0; i < r; i++) {
            result *= (n - i);
        }
        
        return result;
    }
    
    // Calculate sum of array
    public static long sum(int[] array) {
        long sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }
    
    public static double sum(double[] array) {
        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        return sum;
    }
    
    // Calculate average of array
    public static double average(int[] array) {
        if (array.length == 0) return 0;
        return (double) sum(array) / array.length;
    }
    
    public static double average(double[] array) {
        if (array.length == 0) return 0;
        return sum(array) / array.length;
    }
    
    // Find maximum in array
    public static int max(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        int max = array[0];
        for (int num : array) {
            if (num > max) max = num;
        }
        return max;
    }
    
    public static double max(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        double max = array[0];
        for (double num : array) {
            if (num > max) max = num;
        }
        return max;
    }
    
    // Find minimum in array
    public static int min(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        int min = array[0];
        for (int num : array) {
            if (num < min) min = num;
        }
        return min;
    }
    
    public static double min(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        double min = array[0];
        for (double num : array) {
            if (num < min) min = num;
        }
        return min;
    }
    
    // Calculate median
    public static double median(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        int[] sorted = array.clone();
        Sort.quickSort(sorted);
        
        int n = sorted.length;
        if (n % 2 == 0) {
            return (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0;
        } else {
            return sorted[n / 2];
        }
    }
    
    public static double median(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        double[] sorted = array.clone();
        Sort.quickSort(sorted);
        
        int n = sorted.length;
        if (n % 2 == 0) {
            return (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0;
        } else {
            return sorted[n / 2];
        }
    }
    
    // Calculate standard deviation
    public static double standardDeviation(int[] array) {
        if (array.length == 0) return 0;
        
        double mean = average(array);
        double sum = 0;
        for (int num : array) {
            sum += Math.pow(num - mean, 2);
        }
        
        return Math.sqrt(sum / array.length);
    }
    
    public static double standardDeviation(double[] array) {
        if (array.length == 0) return 0;
        
        double mean = average(array);
        double sum = 0;
        for (double num : array) {
            sum += Math.pow(num - mean, 2);
        }
        
        return Math.sqrt(sum / array.length);
    }
    
    // Check if a number is a perfect square
    public static boolean isPerfectSquare(long n) {
        if (n < 0) return false;
        long sqrt = (long) Math.sqrt(n);
        return sqrt * sqrt == n;
    }
    
    // Check if a number is a power of two
    public static boolean isPowerOfTwo(long n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
    
    // Count number of digits in a number
    public static int countDigits(long n) {
        if (n == 0) return 1;
        return (int) Math.log10(Math.abs(n)) + 1;
    }
    
    // Reverse a number
    public static long reverseNumber(long n) {
        long reversed = 0;
        while (n != 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        return reversed;
    }
    
    // Check if a number is palindrome
    public static boolean isPalindrome(long n) {
        return n == reverseNumber(n);
    }
    
    // Calculate absolute value without using Math.abs
    public static long absoluteValue(long n) {
        long mask = n >> 63;
        return (n + mask) ^ mask;
    }
}
