package main.java.calculator.commands;
import main.java.calculator.Calculator;
import main.java.calculator.Command;

import java.util.NoSuchElementException;

public class DivCommand extends Command{
    @Override
    public void execute(String args, Calculator.Context context) throws IllegalArgumentException,
            NoSuchElementException {
        if (!args.isEmpty()){
            throw new IllegalArgumentException("/ must have no arguments!\n");
        }
        if (context.stackGetSize() < 2)
            throw new  NoSuchElementException("Not enough elements in the stack in calculator!\n");
        double value1 = context.stackPop();
        double value2 = context.stackPop();
        if (value2 == 0)
            throw new IllegalArgumentException("Division by zero!\n");
        context.stackPush(value1 / value2);
    }
}

