import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class Main {
	
	// TODO Auto-generated method stub
	public static char getLetterGrade(double grade)
    {
        if (grade > 90) {
            return 'A';
        } else if (grade > 80 && grade < 90) {
            return 'B';
        } else if (grade > 70 && grade < 80) {
            return 'C';
        } else if (grade > 60 && grade < 70) {
            return 'D';
        }
        return 'F';
    }

    public static ArrayList<Character> letterGradeList(String file)
    {
        ArrayList<Character> m_returnList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                // Remove non-numeric characters and trim
                String numbers = line.replaceAll("[^0-9\\s]+", " ").trim();
                String[] columns = numbers.split("\\s+");
                
                // Calculate average if there are grades
                if (columns.length > 0) {
                    double sum = 0;
                    for (String column : columns) {
                        sum += Double.parseDouble(column);
                    }
                    double average = sum / columns.length; // Calculate average
                    m_returnList.add(getLetterGrade(average)); // Get letter grade
                }
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        return m_returnList;
    }


    public static ArrayList<String> grades(String file) {
        ArrayList<String> printValues = new ArrayList<>();
        double value1 = 0;
        double value2 = 0;
        double value3 = 0;
        int iter = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {

            String line;
            while ((line = br.readLine()) != null) {
                // gets rid of the letters in the string
                String numbers = line.replaceAll("[^0-9]+", " ");

                // splits up the numbers by columns in order to
                // add each one to a seperate value to average them
                String[] columns = numbers.split("\\s+");
                if (columns.length >= 4)
                {
                    if (!columns[1].isEmpty()) {
                        value1 += Double.parseDouble(columns[1].trim());
                    }
                    if (!columns[2].isEmpty()) {
                        value2 += Double.parseDouble(columns[2].trim());
                    }
                    if (!columns[3].isEmpty()) {
                        value3 += Double.parseDouble(columns[3].trim());
                    }
                    iter++;
                }
            }
            if (iter > 0) {
                value1 = value1/iter;
                value2 = value2/iter;
                value3 = value3/iter;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // df.format for two decimal places
        // ended up changing to string.format cause returning string
        printValues.add(String.format( "%.2f", value1));
        printValues.add(String.format( "%.2f", value2));
        printValues.add(String.format( "%.2f", value3));        

        return printValues;
   }

   public static void createFile(String file, String averageGrades)
   {
        ArrayList<Character> letterGrade = letterGradeList(file);

        try (FileOutputStream fos = new FileOutputStream("report.txt");
            PrintWriter pw = new PrintWriter(fos);
            BufferedReader br = new BufferedReader(new FileReader(file))) {
            
            String line;
            int iter = 0;
            while ((line = br.readLine()) != null)
            {
                pw.print(line + "\t");
                // print letter grade
                pw.print(letterGrade.get(iter) + "\n");
                iter++;
            }
            // blank space
            pw.println();
            pw.println(averageGrades);
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

   public static void main(String[] args)
   {
        Scanner scnr = new Scanner(System.in);
      
        /* TODO: Declare any necessary variables here. */
        String file = scnr.next();

        /* TODO: Read a file name from the user and read the tsv file here. */
        ArrayList<String> averages = grades(file);

        String m_averagePrinter = "Averages: Midterm1 " + averages.get(0) + ", Midterm2 " + averages.get(1) + ", Final " + averages.get(2);

        /* TODO: Compute student grades and exam averages, then output results to a text file here. */
        createFile(file, m_averagePrinter);
   }


}
