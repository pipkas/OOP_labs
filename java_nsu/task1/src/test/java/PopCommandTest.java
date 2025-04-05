package test.java;

import main.java.calculator.Calculator;
import main.java.calculator.commands.PopCommand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PopCommandTest {
    private Calculator.Context context;
    private PopCommand popCommand;

    @BeforeEach
    void setUp() {
        context = new Calculator.Context();
        popCommand = new PopCommand();
    }

    @Test
    void popTest() throws IllegalArgumentException {
        context.stackPush(5.0);
        popCommand.execute("", context);
        Assertions.assertEquals(0, context.stackGetSize());
    }

    @Test
    void popIncorrectNumberArgsTest() {
        assertThrows(NoSuchElementException.class, () -> popCommand.execute( "", context));
    }

    @Test
    void popIncorrectArgsTest(){
        context.stackPush(17.0);
        assertThrows(IllegalArgumentException.class, () -> popCommand.execute( "pu", context));
    }
}
