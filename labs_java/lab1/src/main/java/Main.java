package main.java;

import main.java.calculator.Calculator;

import java.io.IOException;
import java.util.logging.Logger;


public class Main {
    static Logger logger = Logger.getLogger(String.valueOf(Main.class));
    public static void main(String[] args) throws IOException {

        Calculator calculator;
        try{
            calculator = switch (args.length) {
                case 0 -> new Calculator();
                case 1 -> new Calculator(args[0]);
                default -> throw new IllegalArgumentException("Please, enter a valid command line argument");
            };
            calculator.work();
        }catch(Exception e){
            logger.severe(e.getMessage());
        }
    }
}