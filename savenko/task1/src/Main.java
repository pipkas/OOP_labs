import model.CsvFile;

import java.io.*;
public class Main {
    public static void main(String[] argc) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new FileWriter("file.csv"))){

            System.out.println("Enter the number of rows: ");
            int rows = Integer.parseInt(reader.readLine());
            System.out.println("Enter the number of columns: ");
            int columns = Integer.parseInt(reader.readLine());

            CsvFile csvFile = new CsvFile(writer, rows, columns);
            csvFile.fillRandomly();
        }
    }
}
