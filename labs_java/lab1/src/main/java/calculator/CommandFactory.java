package main.java.calculator;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import main.java.calculator.Command;

public class CommandFactory {
    private static final String CONFIG_FILE = "config";
    private static final Map<String, Class<? extends main.java.calculator.Command>> commandMap = new HashMap<>();
    static private Logger logger = Logger.getLogger(String.valueOf(CommandFactory.class));

    static{
        loadCommands();
    }

    private static void loadCommands() {
        try (InputStream input = CommandFactory.class.getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Config file not found: " + CONFIG_FILE);
            }

            Properties props = new Properties();
            props.load(input);

            for (String cmdName : props.stringPropertyNames()) {
                String className = props.getProperty(cmdName);
                Class<?> curClass = Class.forName(className);
                commandMap.put(cmdName, curClass.asSubclass(Command.class));
            }
            logger.info("Config file was processed: " + CONFIG_FILE);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to load calculator.commands", e);
        }
    }

    public static Command createCommand(String commandName) throws NoSuchMethodException, IllegalArgumentException{
        Class<? extends Command> commandClass = commandMap.get(commandName);
        if (commandClass == null) {
            throw new IllegalArgumentException("Unknown command: " + commandName);
        }
        try {
            return commandClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new NoSuchMethodException();
        }
    }
}



