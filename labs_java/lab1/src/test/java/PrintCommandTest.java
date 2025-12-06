package test.java;

import main.java.calculator.Calculator;
import main.java.calculator.commands.PrintCommand;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrintCommandTest {
    private Calculator.Context context;
    private PrintCommand printCommand;

    @BeforeEach
    void setUp() {
        context = new Calculator.Context();
        printCommand = new PrintCommand();//сюда можно добавить то, что везде повторяться будет
    }

    @Test
    void printTest() throws IOException {
        context.stackPush(5.0);

        PrintStream originalOut = System.out;

        try (ByteArrayOutputStream outContent = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(outContent)) {

            System.setOut(printStream);
            printCommand.execute("", context);

            assertEquals("5.0" + System.lineSeparator(), outContent.toString());

        } finally {

            System.setOut(originalOut);
        }
    }

    @Test
    void printIncorrectNumberArgsTest() {
        assertThrows(NoSuchElementException.class, () -> printCommand.execute( "", context));
    }

    @Test
    void printIncorrectArgsTest(){
        context.stackPush(17.0);
        assertThrows(IllegalArgumentException.class, () -> printCommand.execute( "pu", context));
    }
}
