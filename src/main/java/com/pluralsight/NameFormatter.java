
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

        //if is not null and isnt blank add to result
        if (prefix != null && !prefix.isBlank()) {
            result += prefix.trim() + " ";
        }

        result += firstName;

        //if is not null and isnt blank add to result

        if (middleName != null && !middleName.isBlank()) {
            result += " " + middleName.trim();
        }

        //if is not null and isnt blank add to result

        if (suffix != null && !suffix.isBlank()) {
            result += ", " + suffix.trim();
        }

        return result;
    }

    // Method 3: Format an existing full name
    public static String format(String fullName) {


        // Splits at spaces and commas
        String[] parts = fullName.trim().split("[,\\s]+");

        String prefix = "";
        String firstName = "";
        String middleName = "";
        String lastName = "";
        String suffix = "";

        int start = 0;
        int end = parts.length - 1;

        // Checks for a comma after a suffix
        if (parts[end].contains(",")) {
            suffix = parts[end];
            //Take index 1 down to last name
            end--;
            //Check for the comma on last name meaning there is a suffix
        } else if (end > 0 && parts[end - 1].endsWith(",")) {
            suffix = parts[end];
            //Take index 1 down to last name
            end--;
        }

        // Remove a trailing comma from the last name
        if (end >= 0) {
            parts[end] = parts[end].replace(",", "");
        }

        // Check for posible prefixs
        if (parts[start].equalsIgnoreCase("Mr.") ||
                parts[start].equalsIgnoreCase("Mrs.") ||
                parts[start].equalsIgnoreCase("Ms.") ||
                parts[start].equalsIgnoreCase("Dr.")) {

            prefix = parts[start];
            start++;
        }

        // Get the first name after prefix
        if (start <= end) {
            firstName = parts[start];
            start++;
        }

        // Get the last name
        if (start <= end) {
            lastName = parts[end];
            end--;
        }

        // Any remaining parts are the middle name since index 2
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
