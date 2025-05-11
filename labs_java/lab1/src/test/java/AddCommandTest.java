package test.java;

import main.java.calculator.Calculator;
import main.java.calculator.commands.AddCommand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    private Calculator.Context context;
    private AddCommand addCommand;

    @BeforeEach
    void setUp() {
        context = new Calculator.Context();
        addCommand = new AddCommand();
    }

    @Test
    void addTest(){
        context.stackPush(7.0);
        context.stackPush(17.0);
        addCommand.execute("", context);
        Assertions.assertEquals(24.0, context.stackPeek());
        Assertions.assertEquals(1, context.stackGetSize());
    }

    @Test
    void addIncorrectNumberArgsTest() {
        context.stackPush(7.0);
        assertThrows(NoSuchElementException.class, () -> addCommand.execute( "", context));
    }

    @Test
    void addIncorrectArgsTest(){
        context.stackPush(7.0);
        context.stackPush(17.0);
        assertThrows(IllegalArgumentException.class, () -> addCommand.execute( "pu", context));
    }
}