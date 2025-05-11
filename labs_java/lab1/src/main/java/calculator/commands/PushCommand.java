package main.java.calculator.commands;
import main.java.calculator.Calculator;
import main.java.calculator.Command;

import java.util.NoSuchElementException;

public class PushCommand extends Command {
    @Override
    public void execute(String args, Calculator.Context context) throws IllegalArgumentException,
            NoSuchElementException {
        String[] parts = args.split(" ");
        if (parts.length != 1 || args.isEmpty()) {
            throw new IllegalArgumentException("Wrong number of arguments in PUSH command!");
        }
        try{
            double value = Double.parseDouble(parts[0]);
            context.stackPush(value);
        }catch(NumberFormatException e){
            double value = context.getVar(parts[0]);
            context.stackPush(value);
        }

    }
}
