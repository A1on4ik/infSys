import java.io.*; //The java.io library is necessary in Java for reading and writing data to and from external sources, such as files
import java.util.regex.*;

public class DataChecker {
    public static void main(String[] args) throws IOException {
        // IOException is an exception that is thrown in Java when an input/output
        // (I/O) operation fails. I/O operations can fail due to various reasons,
        // such as a missing file, a corrupted file, or a problem with the file system.

        String inputFile = "input.txt"; // Input file path
        String outputFile = "output.txt"; // Output file path
        BufferedReader reader = new BufferedReader(new FileReader(inputFile)); // Read from the input file
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile)); // Write to the output file

        String namePattern = "^[a-zA-Z]+\\s*[a-zA-Z]+$"; // Regex pattern for the name
        String agePattern = "^-?\\d+$"; // Regex pattern for the age
        String phonePattern = "^\\+?\\d{1,3}\\s*\\(?\\s*\\d{3}\\s*\\)?\\s*\\d{1,7}$"; // Regex pattern for the phone number
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$"; // Regex pattern for the email

        //  "^": Matches the start of the string
        //  "\\+": Matches a literal plus sign (+)
        //  "\\d{1,3}": Matches one to three digits (0-9)
        //  " ": Matches a space character
        //  "$": Matches the end of the string
        //  "?": Optional 0 or 1 match
        //  "\\d+": Matches one or more consecutive digits in a string.
        //  "[a-zA-Z]{2,}": Matches two or more characters that can be any letter,
        //  "\\s*": Matches one or more spaces

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|"); // Split the line into parts

            // in the Java code is used to split a string into multiple parts based on a specified delimiter.
            // In this case, the line string is being split into multiple parts using the | (vertical bar) character as the delimiter.
            // The \\ characters before the | are necessary to escape the | character, since it has special meaning as a regex metacharacter.


            // Check if each part matches the regex pattern
            Matcher nameMatcher = Pattern.compile(namePattern).matcher(parts[0]);
            Matcher ageMatcher = Pattern.compile(agePattern).matcher(parts[1]);
            Matcher phoneMatcher = Pattern.compile(phonePattern).matcher(parts[2]);
            Matcher emailMatcher = Pattern.compile(emailPattern).matcher(parts[3]);


            // Correct the data if necessary
            if (!nameMatcher.matches()) {
                parts[0] = "";
            }
            if (!ageMatcher.matches()) {
                parts[1] = "";
            }
            if (!phoneMatcher.matches()) {
                parts[2] = ""; parts[2].replaceAll("^\\+\\d{1,3} (\\d{3}) (\\d{1,7})$", "($1) $2-$3");
            }
            if (!emailMatcher.matches()) {
                parts[3] = "";
            }

            // Join the corrected parts into a single line
            String correctedLine = String.join("|", parts);
            writer.write(correctedLine);
            writer.newLine();
        }

        reader.close();
        writer.close();
    }
}
