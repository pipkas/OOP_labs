package test.java;

import main.java.calculator.Calculator;
import main.java.calculator.commands.SubCommand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubCommandTest {
    private Calculator.Context context;
    private SubCommand subCommand;

    @BeforeEach
    void setUp() {
        context = new Calculator.Context();
        subCommand = new SubCommand();
    }

    @Test
    void subTest() throws IllegalArgumentException {
        context.stackPush(7.0);
        context.stackPush(17.0);
        subCommand.execute("", context);
        Assertions.assertEquals(10.0, context.stackPeek());
        Assertions.assertEquals(1, context.stackGetSize());
    }

    @Test
    void subIncorrectNumberArgsTest() {
        context.stackPush(7.0);
        assertThrows(NoSuchElementException.class, () -> subCommand.execute( "", context));
    }

    @Test
    void subIncorrectArgsTest(){
        context.stackPush(7.0);
        context.stackPush(17.0);
        assertThrows(IllegalArgumentException.class, () -> subCommand.execute( "pu", context));
    }
}
