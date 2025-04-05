package main.java.calculator;

import java.util.NoSuchElementException;

public abstract class Command {
    abstract public void execute(String args, Calculator.Context context)
                                      throws IllegalArgumentException, NoSuchElementException;
}




