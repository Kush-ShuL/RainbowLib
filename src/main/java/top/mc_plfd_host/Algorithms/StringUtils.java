package top.mc_plfd_host.Algorithms;

import java.util.*;
import java.util.regex.Pattern;

public class StringUtils {
    
    // Reverse string - O(n)
    public static String reverse(String str) {
        if (str == null) return null;
        return new StringBuilder(str).reverse().toString();
    }
    
    // Check if string is palindrome - O(n)
    public static boolean isPalindrome(String str) {
        if (str == null) return false;
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    // Check if string is palindrome (ignoring case and non-alphanumeric)
    public static boolean isPalindromeIgnoreCase(String str) {
        if (str == null) return false;
        int left = 0, right = str.length() - 1;
        
        while (left < right) {
            char leftChar = Character.toLowerCase(str.charAt(left));
            char rightChar = Character.toLowerCase(str.charAt(right));
            
            // Skip non-alphanumeric characters
            while (left < right && !Character.isLetterOrDigit(leftChar)) {
                left++;
                leftChar = Character.toLowerCase(str.charAt(left));
            }
            while (left < right && !Character.isLetterOrDigit(rightChar)) {
                right--;
                rightChar = Character.toLowerCase(str.charAt(right));
            }
            
            if (leftChar != rightChar) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    // Count occurrences of a character - O(n)
    public static int countChar(String str, char ch) {
        if (str == null) return 0;
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }
    
    // Count occurrences of a substring - O(n*m) in worst case
    public static int countSubstring(String str, String sub) {
        if (str == null || sub == null || sub.isEmpty()) return 0;
        
        int count = 0;
        int index = 0;
        
        while ((index = str.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }
        
        return count;
    }
    
    // Remove all occurrences of a character - O(n)
    public static String removeChar(String str, char ch) {
        if (str == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ch) {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
    
    // Remove all occurrences of a substring - O(n)
    public static String removeSubstring(String str, String sub) {
        if (str == null || sub == null) return str;
        return str.replace(sub, "");
    }
    
    // Remove duplicate characters - O(n²) naive, O(n) with additional space
    public static String removeDuplicates(String str) {
        if (str == null) return null;
        Set<Character> seen = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!seen.contains(ch)) {
                seen.add(ch);
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }
    
    // Check if two strings are anagrams - O(n log n)
    public static boolean areAnagrams(String str1, String str2) {
        if (str1 == null || str2 == null) return str1 == str2;
        if (str1.length() != str2.length()) return false;
        
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        return Arrays.equals(arr1, arr2);
    }
    
    // Check if two strings are anagrams (case insensitive) - O(n log n)
    public static boolean areAnagramsIgnoreCase(String str1, String str2) {
        if (str1 == null || str2 == null) return str1 == str2;
        if (str1.length() != str2.length()) return false;
        
        char[] arr1 = str1.toLowerCase().toCharArray();
        char[] arr2 = str2.toLowerCase().toCharArray();
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        return Arrays.equals(arr1, arr2);
    }
    
    // Find longest common prefix - O(n)
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        
        return prefix;
    }
    
    // Find longest common substring - O(n²)
    public static String longestCommonSubstring(String str1, String str2) {
        if (str1 == null || str2 == null) return "";
        
        int m = str1.length();
        int n = str2.length();
        int maxLength = 0;
        int endIndex = 0;
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }
        
        return str1.substring(endIndex - maxLength, endIndex);
    }
    
    // Find all permutations of a string - O(n!)
    public static List<String> permutations(String str) {
        List<String> result = new ArrayList<>();
        if (str == null) return result;
        
        permutationsHelper(str.toCharArray(), 0, result);
        return result;
    }
    
    private static void permutationsHelper(char[] chars, int index, List<String> result) {
        if (index == chars.length - 1) {
            result.add(new String(chars));
            return;
        }
        
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            permutationsHelper(chars, index + 1, result);
            swap(chars, index, i); // Backtrack
        }
    }
    
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    
    // Find all substrings of a string - O(n²)
    public static List<String> substrings(String str) {
        List<String> result = new ArrayList<>();
        if (str == null) return result;
        
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                result.add(str.substring(i, j));
            }
        }
        
        return result;
    }
    
    // Check if string contains only digits - O(n)
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) return false;
        
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    // Check if string contains only letters - O(n)
    public static boolean isAlphabetic(String str) {
        if (str == null || str.isEmpty()) return false;
        
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    // Check if string contains only alphanumeric characters - O(n)
    public static boolean isAlphanumeric(String str) {
        if (str == null || str.isEmpty()) return false;
        
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    // Capitalize first letter of each word - O(n)
    public static String capitalizeWords(String str) {
        if (str == null || str.isEmpty()) return str;
        
        String[] words = str.split("\\s+");
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                sb.append(Character.toUpperCase(words[i].charAt(0)));
                sb.append(words[i].substring(1).toLowerCase());
            }
            if (i < words.length - 1) {
                sb.append(" ");
            }
        }
        
        return sb.toString();
    }
    
    // Convert to camelCase - O(n)
    public static String toCamelCase(String str) {
        if (str == null || str.isEmpty()) return str;
        
        String[] words = str.split("[\\s_-]+");
        StringBuilder sb = new StringBuilder();
        
        sb.append(words[0].toLowerCase());
        for (int i = 1; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                sb.append(Character.toUpperCase(words[i].charAt(0)));
                sb.append(words[i].substring(1).toLowerCase());
            }
        }
        
        return sb.toString();
    }
    
    // Convert to PascalCase - O(n)
    public static String toPascalCase(String str) {
        if (str == null || str.isEmpty()) return str;
        
        String[] words = str.split("[\\s_-]+");
        StringBuilder sb = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                sb.append(word.substring(1).toLowerCase());
            }
        }
        
        return sb.toString();
    }
    
    // Convert to snake_case - O(n)
    public static String toSnakeCase(String str) {
        if (str == null || str.isEmpty()) return str;
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) sb.append('_');
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
    
    // Convert to kebab-case - O(n)
    public static String toKebabCase(String str) {
        return toSnakeCase(str).replace('_', '-');
    }
    
    // Levenshtein distance (edit distance) - O(m*n)
    public static int levenshteinDistance(String str1, String str2) {
        if (str1 == null || str2 == null) return -1;
        
        int m = str1.length();
        int n = str2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize base cases
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        
        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        Math.min(dp[i - 1][j], dp[i][j - 1]),
                        dp[i - 1][j - 1]
                    );
                }
            }
        }
        
        return dp[m][n];
    }
    
    // Check if string is a valid email - O(n)
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    
    // Check if string is a valid URL - O(n)
    public static boolean isValidUrl(String url) {
        if (url == null) return false;
        
        String urlRegex = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
        Pattern pattern = Pattern.compile(urlRegex);
        return pattern.matcher(url).matches();
    }
    
    // Compress string by removing consecutive duplicates - O(n)
    public static String compress(String str) {
        if (str == null || str.isEmpty()) return str;
        
        StringBuilder sb = new StringBuilder();
        char prev = str.charAt(0);
        sb.append(prev);
        
        for (int i = 1; i < str.length(); i++) {
            char current = str.charAt(i);
            if (current != prev) {
                sb.append(current);
                prev = current;
            }
        }
        
        return sb.toString();
    }
    
    // Decompress string (reverse of compress) - O(n)
    public static String decompress(String str) {
        if (str == null || str.isEmpty()) return str;
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            sb.append(c);
            // Add the same character if next character is different
            if (i < str.length() - 1 && str.charAt(i + 1) != c) {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
    
    // Find first non-repeating character - O(n)
    public static char firstNonRepeatingChar(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("String is empty");
        }
        
        Map<Character, Integer> countMap = new LinkedHashMap<>();
        
        // Count occurrences
        for (char c : str.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        
        // Find first character with count 1
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        
        throw new IllegalArgumentException("No non-repeating character found");
    }
    
    // Find most frequent character - O(n)
    public static char mostFrequentChar(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("String is empty");
        }
        
        Map<Character, Integer> countMap = new HashMap<>();
        int maxCount = 0;
        char result = str.charAt(0);
        
        for (char c : str.toCharArray()) {
            int count = countMap.getOrDefault(c, 0) + 1;
            countMap.put(c, count);
            
            if (count > maxCount) {
                maxCount = count;
                result = c;
            }
        }
        
        return result;
    }
    
    // Check if string contains only unique characters - O(n)
    public static boolean hasAllUniqueChars(String str) {
        if (str == null) return true;
        if (str.length() > 128) return false; // ASCII assumption
        
        boolean[] charSet = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (charSet[val]) {
                return false;
            }
            charSet[val] = true;
        }
        return true;
    }
    
    // Rotate string left by k positions - O(n)
    public static String rotateLeft(String str, int k) {
        if (str == null) return null;
        int n = str.length();
        k = k % n;
        return str.substring(k) + str.substring(0, k);
    }
    
    // Rotate string right by k positions - O(n)
    public static String rotateRight(String str, int k) {
        if (str == null) return null;
        int n = str.length();
        k = k % n;
        return str.substring(n - k) + str.substring(0, n - k);
    }
}
