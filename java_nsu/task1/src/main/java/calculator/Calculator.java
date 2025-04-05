package main.java.calculator;

import main.java.Main;
import main.java.calculator.utils.StringProcessing;
import main.java.calculator.utils.WorkWithStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class Calculator {
    private final WorkWithStream workWithStream;

    static private Logger logger = Logger.getLogger(String.valueOf(Calculator.class));

    public Calculator(){
        workWithStream = new WorkWithStream();
    }
    public Calculator(String fileName) throws FileNotFoundException {
        workWithStream = new WorkWithStream(fileName);
    }

    public void work() throws IOException {
        final Calculator.Context context = new Context();
        Consumer<Boolean> flagConsumer = context::setFlagIsStopped;
        String input;
        logger.info("The calculator is running.");
        System.out.println("Please, enter commands: \n");

        while ((input = workWithStream.getString()) != null){
            String[] pair = StringProcessing.process(input, flagConsumer);
            if (context.getFlagIsStopped()){
                logger.info("The calculator is stopped.\n");
                return;
            }
            if (pair != null){
                try{
                    Command commandClass = CommandFactory.createCommand(pair[0].trim());
                    commandClass.execute(pair[1].trim(), context);
                }
                catch(IllegalArgumentException | NoSuchElementException e){
                    logger.warning(e.getMessage());
                }
                catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        workWithStream.close();
        logger.info("Config file was closed.\n");
    }

    static public class Context {
        private boolean flagIsStopped;
        private final Deque<Double> stack;
        private final Map<String, Double> variables;
        public Context(){
            flagIsStopped = false;
            stack = new ArrayDeque<>();
            variables = new HashMap<>();
        }
        public void setFlagIsStopped(boolean flag){
            flagIsStopped = flag;
        }
        public boolean getFlagIsStopped(){
            return flagIsStopped;
        }
        public void stackPush(double value){
            stack.push(value);
        }
        public double stackPop() throws NoSuchElementException {//ловить исключение
            if (stack.isEmpty()) {
                throw new NoSuchElementException("Not enough elements in the calculator's stack");
            }
            return stack.pop();
        }
        public double stackPeek() throws NoSuchElementException{//ловить исключение
            Double value = stack.peek();
            if (value == null)
                throw new NoSuchElementException("Not enough elements in the calculator's stack");
            return value;
        }
        public int stackGetSize(){
            return stack.size();
        }
        public void addVar(String name, double value){
            variables.put(name, value);
        }
        public double getVar(String name) throws NoSuchElementException{
            Double value = variables.get(name);
            if (value == null)
                throw new NoSuchElementException("The variable `" + name + "` is missing from the massive with valid values!");
            return variables.get(name);
        }

    }
}
