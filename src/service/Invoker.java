package service;

import instructions.Command;

public interface Invoker {
    void setCommand(String commandName, Command command);
    void invoke();
    boolean scan();
}
