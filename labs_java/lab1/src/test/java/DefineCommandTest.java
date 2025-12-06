package test.java;

import main.java.calculator.Calculator;
import main.java.calculator.commands.DefineCommand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class DefineCommandTest {
    private Calculator.Context context;
    private DefineCommand defineCommand;

    @BeforeEach
    void setUp() {
        context = new Calculator.Context();
        defineCommand = new DefineCommand();
    }

    @Test
    void defineTest() throws IllegalArgumentException {
        defineCommand.execute("abc 5", context);
        Assertions.assertEquals(5.0, context.getVar("abc"));
        Assertions.assertEquals(0, context.stackGetSize());
    }

    @Test
    void defineIncorrectNumberArgsTest() {
        assertThrows(IllegalArgumentException.class, () -> defineCommand.execute( "", context));
        assertThrows(IllegalArgumentException.class, () -> defineCommand.execute( "myau 1 2", context));
    }

    @Test
    void defineIncorrectArgsTest(){
        assertThrows(IllegalArgumentException.class, () -> defineCommand.execute( "i love vova", context));
    }
}
