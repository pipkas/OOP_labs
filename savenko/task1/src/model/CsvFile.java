package model;

import java.util.*;
import java.io.*;

public class CsvFile implements Closeable{
    private final BufferedWriter file;
    private final int columns;
    private final int rows;
    List<Integer> data;

    public CsvFile(BufferedWriter file, int rows, int columns) throws IllegalArgumentException, IOException {
        if (columns <= 0 || rows <= 0)
            throw new IllegalArgumentException("The number of rows and columns must be greater than 0");
        if (file == null)
            throw new IOException("Writer is null. Cannot write to file.");
        this.file = file;
        this.columns = columns;
        this.rows = rows;
        this.data = new ArrayList<>(this.columns * this.rows);
    }

    public void fillRandomly() throws IOException {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int randomNumber = random.nextInt(100);
                data.add(randomNumber);
                file.write(String.valueOf(randomNumber));
                if (j < columns - 1)
                    file.write(',');
            }
            file.write('\n');
        }
        file.flush();
    }

    public void close() throws IOException {
        file.close();
    }
}
