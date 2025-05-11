package test.java;

import main.java.calculator.Calculator;
import main.java.calculator.utils.StringProcessing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class StringProcessingTest {
    private Calculator.Context context;
    private Consumer<Boolean> flagConsumer;
    private String[] pair;

    @BeforeEach
    public void setUp() {
        context = new Calculator.Context();
        flagConsumer = context::setFlagIsStopped;
    }

    @Test
    void processTest() {
        pair = StringProcessing.process("Hello World", flagConsumer);
        assert pair != null;
        Assertions.assertFalse(context.getFlagIsStopped());
        Assertions.assertEquals("Hello", pair[0]);
        Assertions.assertEquals("World", pair[1]);
    }

    @Test
    void processStopTest() {
        pair = StringProcessing.process("stop", flagConsumer);
        assert pair == null;
        Assertions.assertTrue(context.getFlagIsStopped());
    }

    @Test
    void processShouldBePairTest() {
        pair = StringProcessing.process("", flagConsumer);
        assert pair != null;
        Assertions.assertEquals("", pair[0]);
        Assertions.assertEquals("", pair[1]);
        Assertions.assertFalse(context.getFlagIsStopped());

        pair = StringProcessing.process("Hello", flagConsumer);
        assert pair != null;
        Assertions.assertEquals("Hello", pair[0]);
        Assertions.assertEquals("", pair[1]);
        Assertions.assertFalse(context.getFlagIsStopped());
    }
}
