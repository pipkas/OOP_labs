package test.java;

import main.java.calculator.Calculator;
import main.java.calculator.commands.PushCommand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PushCommandTest {
    private Calculator.Context context;
    private PushCommand pushCommand;

    @BeforeEach
    void setUp() {
        context = new Calculator.Context();
        pushCommand = new PushCommand();
    }

    @Test
    void pushNumberTest(){
        pushCommand.execute("4", context);
        Assertions.assertEquals(4.0, context.stackPeek());
        Assertions.assertEquals(1, context.stackGetSize());
    }

    @Test
    void pushIncorrectArgsTest() {
        assertThrows(IllegalArgumentException.class, () -> pushCommand.execute( "", context));
        assertThrows(IllegalArgumentException.class, () -> pushCommand.execute( "5 8", context));
    }

    @Test
    void pushNotANumberTest(){
        context.addVar("abc", 4.0);
        pushCommand.execute("abc", context);
        Assertions.assertEquals(4.0, context.stackPeek());
        Assertions.assertEquals(1, context.stackGetSize());
    }

    @Test
    void pushIncorrestNotANumberTest(){
        assertThrows(NoSuchElementException.class, () -> pushCommand.execute( "abc", context));
    }
}
