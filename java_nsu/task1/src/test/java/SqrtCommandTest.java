package test.java;

import main.java.calculator.Calculator;
import main.java.calculator.commands.SqrtCommand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SqrtCommandTest {
    private Calculator.Context context;
    private SqrtCommand sqrtCommand;

    @BeforeEach
    void setUp() {
        context = new Calculator.Context();
        sqrtCommand = new SqrtCommand();
    }

    @Test
    void sqrtTest() throws IllegalArgumentException {
        context.stackPush(25.0);
        sqrtCommand.execute("", context);
        Assertions.assertEquals(5.0, context.stackPeek());
        Assertions.assertEquals(1, context.stackGetSize());
    }

    @Test
    void sqrtIncorrectNumberArgsTest() {
        assertThrows(NoSuchElementException.class, () -> sqrtCommand.execute( "", context));
    }

    @Test
    void sqrtIncorrectArgsTest(){
        context.stackPush(17.0);
        assertThrows(IllegalArgumentException.class, () -> sqrtCommand.execute( "pu", context));
    }
}
