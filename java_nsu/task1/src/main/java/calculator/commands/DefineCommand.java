package main.java.calculator.commands;
import main.java.calculator.Calculator;
import main.java.calculator.Command;

public class DefineCommand extends Command {
    @Override
    public void execute(String args, Calculator.Context context) throws IllegalArgumentException{
        String[] parts = args.split(" ");
        if (parts.length != 2){
            throw new IllegalArgumentException("Wrong number of arguments in DEFINE command!");
        }
        try{
            double value = Double.parseDouble(parts[1]);
            context.addVar(parts[0], value);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The second argument in DEFINE command is not a number!");
        }
    }
}
