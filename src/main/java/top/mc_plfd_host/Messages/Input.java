package top.mc_plfd_host.Messages;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Input {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    public static String string(String prompt) {
        Print.plain(prompt);
        return scanner.nextLine();
    }
    
    public static String string() {
        return scanner.nextLine();
    }
    
    public static int integer(String prompt) {
        return integer(prompt, "Please enter a valid integer!");
    }
    
    public static int integer(String prompt, String errorMessage) {
        while (true) {
            try {
                Print.plain(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                Print.error(errorMessage);
            }
        }
    }
    
    public static int integer() {
        return integer("Please enter an integer:", "Please enter a valid integer!");
    }
    
    public static long longNum(String prompt) {
        return longNum(prompt, "Please enter a valid long integer!");
    }
    
    public static long longNum(String prompt, String errorMessage) {
        while (true) {
            try {
                Print.plain(prompt);
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                Print.error(errorMessage);
            }
        }
    }
    
    public static long longNum() {
        return longNum("Please enter a long integer:", "Please enter a valid long integer!");
    }
    
    public static double decimal(String prompt) {
        return decimal(prompt, "Please enter a valid number!");
    }
    
    public static double decimal(String prompt, String errorMessage) {
        while (true) {
            try {
                Print.plain(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                Print.error(errorMessage);
            }
        }
    }
    
    public static double decimal() {
        return decimal("Please enter a number:", "Please enter a valid number!");
    }
    
    public static float floatNum(String prompt) {
        return floatNum(prompt, "Please enter a valid float number!");
    }
    
    public static float floatNum(String prompt, String errorMessage) {
        while (true) {
            try {
                Print.plain(prompt);
                return Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                Print.error(errorMessage);
            }
        }
    }
    
    public static float floatNum() {
        return floatNum("Please enter a float number:", "Please enter a valid float number!");
    }
    
    public static boolean bool(String prompt) {
        return bool(prompt, "Please enter y/n or yes/no!");
    }
    
    public static boolean bool(String prompt, String errorMessage) {
        while (true) {
            Print.plain(prompt + " (y/n)");
            String input = scanner.nextLine().toLowerCase().trim();
            if (input.equals("y") || input.equals("yes") || input.equals("true") || input.equals("1")) {
                return true;
            } else if (input.equals("n") || input.equals("no") || input.equals("false") || input.equals("0")) {
                return false;
            } else {
                Print.error(errorMessage);
            }
        }
    }
    
    public static boolean bool() {
        return bool("Please enter a boolean value:", "Please enter y/n or yes/no!");
    }
    
    public static char character(String prompt) {
        return character(prompt, "Input cannot be empty!");
    }
    
    public static char character(String prompt, String errorMessage) {
        Print.plain(prompt);
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            Print.error(errorMessage);
            return character(prompt, errorMessage);
        }
        return input.charAt(0);
    }
    
    public static char character() {
        return character("Please enter a character:", "Input cannot be empty!");
    }
    
    public static String select(String prompt, String... options) {
        return select(prompt, "Please enter a valid option!", "Please enter a number!", options);
    }
    
    public static String select(String prompt, String rangeErrorMessage, String formatErrorMessage, String... options) {
        while (true) {
            Print.plain(prompt);
            for (int i = 0; i < options.length; i++) {
                Print.plain((i + 1) + ". " + options[i]);
            }
            Print.plain("Please select (1-" + options.length + "):");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= options.length) {
                    return options[choice - 1];
                } else {
                    Print.error(rangeErrorMessage);
                }
            } catch (NumberFormatException e) {
                Print.error(formatErrorMessage);
            }
        }
    }
    
    public static String select(String... options) {
        return select("Please select:", "Please enter a valid option!", "Please enter a number!", options);
    }
    
    public static String confirm(String prompt, String defaultValue) {
        Print.plain(prompt + " (default: " + defaultValue + ")");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? defaultValue : input;
    }
    
    public static String confirm(String prompt) {
        return confirm(prompt, "");
    }
    
    public static String password(String prompt) {
        if (System.console() != null) {
            char[] password = System.console().readPassword(prompt);
            return new String(password);
        } else {
            Print.warn("Current environment does not support password hiding, will be displayed in plain text");
            return string(prompt);
        }
    }
    
    public static String[] multiLine(String prompt, String endKeyword) {
        Print.plain(prompt + " (enter '" + endKeyword + "' to end)");
        List<String> lines = new ArrayList<>();
        String line;
        
        while (!(line = scanner.nextLine()).equals(endKeyword)) {
            lines.add(line);
        }
        
        return lines.toArray(new String[0]);
    }
    
    public static String[] multiLine(String prompt) {
        return multiLine(prompt, "END");
    }
    
    public static List<String> stringList(String prompt, String delimiter) {
        Print.plain(prompt + " (separated by " + delimiter + ")");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(input.split(delimiter));
    }
    
    public static List<String> stringList(String prompt) {
        return stringList(prompt, ",");
    }
    
    public static void close() {
        scanner.close();
    }
}
