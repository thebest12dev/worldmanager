package com.thebest12lines.util;

public class Converter {
    public static String convertToHex(String text) {
        StringBuilder hexResult = new StringBuilder();

        for (char c : text.toCharArray()) {
            // Convert each character to its hexadecimal representation
            String hexChar = Integer.toHexString(c);
            hexResult.append(padWithZeroes(hexChar, 2)); // Always pad with 2 zeroes
        }

        return hexResult.toString();
    }

    public static String convertToHex(int decimalValue) {
        // Convert the decimal value to its hexadecimal representation
        return Integer.toHexString(decimalValue);
    }

    public static String convertToHex(int decimalValue, int numZeroes) {
        // Convert the decimal value to its hexadecimal representation
        String hexValue = Integer.toHexString(decimalValue);
        return padWithZeroes(hexValue, numZeroes);
    }

    private static String padWithZeroes(String input, int desiredLength) {
        int currentLength = input.length();
        if (currentLength >= desiredLength) {
            return input; // No need to pad
        } else {
            int numZeroesToAdd = desiredLength - currentLength;
            StringBuilder paddedValue = new StringBuilder();
            for (int i = 0; i < numZeroesToAdd; i++) {
                paddedValue.append("0");
            }
            paddedValue.append(input);
            return paddedValue.toString();
        }
    }

    public static String convertToHex(long value, int numZeroes) {
       
            // Convert the decimal value to its hexadecimal representation
            String hexValue = Long.toHexString(value);
            return padWithZeroes(hexValue, numZeroes);
        
    }
    public static String convertToHex(float value, int numZeroes) {
       
        // Convert the decimal value to its hexadecimal representation
        int intValue = Float.floatToIntBits(value);
        String hexValue = Integer.toHexString(intValue);
        
        return padWithZeroes(hexValue, numZeroes);
    
} public static String convertToHex(double value, int numZeroes) {
       
    // Convert the decimal value to its hexadecimal representation
    long intValue = Double.doubleToRawLongBits(value);
    String hexValue = Long.toHexString(intValue);
    
    return padWithZeroes(hexValue, numZeroes);

}
}
