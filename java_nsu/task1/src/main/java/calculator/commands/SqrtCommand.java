package main.java.calculator.commands;
import main.java.calculator.Calculator;
import main.java.calculator.Command;

import java.util.NoSuchElementException;

public class SqrtCommand extends Command {
    @Override
    public void execute(String args, Calculator.Context context) throws IllegalArgumentException,
                                                                        NoSuchElementException {
        if (!args.isEmpty()){
            throw new IllegalArgumentException("SQRT command must have no arguments!\n");
        }

        double value = context.stackPop();
        context.stackPush(Math.sqrt(value));
    }
}
