
package com.pluralsight;

public class NameFormatter {

    private NameFormatter() {
    }

    // Method 1: First and last name
    public static String format(String firstName, String lastName) {
        return lastName + ", " + firstName;
    }

    // Method 2: Full name with optional components
    public static String format(String prefix, String firstName,
                                String middleName, String lastName,
                                String suffix) {

        String result = lastName + ", ";

        //if is not null and isnt blank
        if (prefix != null && !prefix.isBlank()) {
            result += prefix.trim() + " ";
        }

        result += firstName;

        if (middleName != null && !middleName.isBlank()) {
            result += " " + middleName.trim();
        }

        if (suffix != null && !suffix.isBlank()) {
            result += ", " + suffix.trim();
        }

        return result;
    }

    // Method 3: Format an existing full name
    public static String format(String fullName) {

        String[] parts = fullName.trim().split("\\s+");

        String prefix = "";
        String firstName = "";
        String middleName = "";
        String lastName = "";
        String suffix = "";

        int start = 0;
        int end = parts.length - 1;

        // Check for a suffix after a comma
        if (parts[end].contains(",")) {
            suffix = parts[end];
            end--;
        } else if (end > 0 && parts[end - 1].endsWith(",")) {
            suffix = parts[end];
            end--;
        }

        // Remove a trailing comma from the last name
        if (end >= 0) {
            parts[end] = parts[end].replace(",", "");
        }

        // Check for a prefix
        if (parts[start].equalsIgnoreCase("Mr.") ||
                parts[start].equalsIgnoreCase("Mrs.") ||
                parts[start].equalsIgnoreCase("Ms.") ||
                parts[start].equalsIgnoreCase("Dr.")) {

            prefix = parts[start];
            start++;
        }

        // Get the first name
        if (start <= end) {
            firstName = parts[start];
            start++;
        }

        // Get the last name
        if (start <= end) {
            lastName = parts[end];
            end--;
        }

        // Any remaining parts are the middle name
        while (start <= end) {
            if (!middleName.isEmpty()) {
                middleName += " ";
            }

            middleName += parts[start];
            start++;
        }

        return format(prefix, firstName, middleName, lastName, suffix);
    }
}
