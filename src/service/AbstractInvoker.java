package service;

import instructions.Command;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractInvoker implements Invoker {
    protected Map<String, Command> dictionary = new HashMap<>();

    @Override
    public void setCommand(String commandName, Command command) {

    }

    @Override
    public void invoke() {

    }

    @Override
    public boolean scan() {
        return false;
    }
}
