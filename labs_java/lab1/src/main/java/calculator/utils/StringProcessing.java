package main.java.calculator.utils;

import java.util.function.Consumer;

public final class StringProcessing {
    static public String[] process(String string, Consumer<Boolean> setFlagIsStopped){

        String[] parts = string.trim().split(" ", 2);
        if (parts.length == 1) {
            parts = new String[]{parts[0], ""};
        }
        if (parts[0].trim().equalsIgnoreCase("stop")){
            setFlagIsStopped.accept(true);
            return null;
        }
        return parts;
    }
}
