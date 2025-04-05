package test.java;

import main.java.calculator.Calculator;
import main.java.calculator.commands.MultCommand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultCommandTest {
    private Calculator.Context context;
    private MultCommand multCommand;

    @BeforeEach
    void setUp() {
        context = new Calculator.Context();
        multCommand = new MultCommand();
    }

    @Test
    void multTest() throws IllegalArgumentException {
        context.stackPush(5.0);
        context.stackPush(20.0);
        multCommand.execute("", context);
        Assertions.assertEquals(100.0, context.stackPeek());
        Assertions.assertEquals(1, context.stackGetSize());
    }

    @Test
    void multIncorrectNumberArgsTest() {
        context.stackPush(7.0);
        assertThrows(NoSuchElementException.class, () -> multCommand.execute( "", context));
    }

    @Test
    void multIncorrectArgsTest(){
        context.stackPush(7.0);
        context.stackPush(17.0);
        assertThrows(IllegalArgumentException.class, () -> multCommand.execute( "pu", context));
    }
}
