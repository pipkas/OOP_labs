package main.java.calculator.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class WorkWithStream implements AutoCloseable{
    private final BufferedReader reader;
    public WorkWithStream() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }
    public WorkWithStream(String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    public String getString() throws IOException {
        return reader.readLine();
    }
    public void close() throws IOException {
        reader.close();
    }
}
