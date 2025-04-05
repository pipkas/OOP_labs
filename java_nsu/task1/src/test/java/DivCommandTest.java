package test.java;

import main.java.calculator.Calculator;
import main.java.calculator.commands.DivCommand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DivCommandTest {
    private Calculator.Context context;
    private DivCommand divCommand;

    @BeforeEach
    void setUp() {
        context = new Calculator.Context();
        divCommand = new DivCommand();
    }

    @Test
    void divTest() throws IllegalArgumentException {
        context.stackPush(5.0);
        context.stackPush(20.0);
        divCommand.execute("", context);
        Assertions.assertEquals(4.0, context.stackPeek());
        Assertions.assertEquals(1, context.stackGetSize());
    }

    @Test
    void divIncorrectNumberArgsTest() {
        context.stackPush(7.0);
        assertThrows(NoSuchElementException.class, () -> divCommand.execute( "", context));
    }

    @Test
    void divIncorrectArgsTest(){
        context.stackPush(7.0);
        context.stackPush(17.0);
        assertThrows(IllegalArgumentException.class, () -> divCommand.execute( "pu", context));
    }
}
