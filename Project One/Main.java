import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Main {

    public static ArrayList<String> inputReader(String textFile) {
        ArrayList<String> outputList = new ArrayList<>(); 

        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            String content;
            while ((content = br.readLine()) != null) {
                if (content.contains("Not available"))
                    continue;

                // \\s{2,} checks for two white spaces
                // \\t checks for a tab character need two cause string literal
                String[] columns = content.split("\\s{2,}|\\t");

                if (columns.length >= 3) {
                    String category = columns[0].trim();     // Category
                    String item = columns[1].trim();         // Item name
                    String description = columns[2].trim();  // Description

                    // Rearrange and format the output
                    String output = item + " (" + category + ") -- " + description;
                    outputList.add(output);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputList;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scnr = new Scanner(System.in);

        /* Type your code here. */
        ArrayList<String> list = new ArrayList<>();
        String link = scnr.next();

        ArrayList<String> result = inputReader(link); // Get results for the current file
        for (String item : result) {
            if (!list.contains(item)) { // Check for duplicates
                list.add(item); // Only add if it's not already present
            }
        }
        
        for (String printer : list)
        {
            System.out.println(printer);
        }
	}

}
