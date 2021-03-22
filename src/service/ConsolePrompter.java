package service;

import instructions.Command;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsolePrompter extends AbstractInvoker {
    private Scanner scanner;
    private PrintStream pipeout;

    public ConsolePrompter(PrintStream pipeout, InputStream pipein) {
        this.pipeout = pipeout;
        scanner = new Scanner(System.in);
    }

    @Override
    public void setCommand(String commandName, Command command) {
        dictionary.put(commandName,command);
    }


    public boolean scan() {
        String reply = "";

        while (reply.equals("")) {
            pipeout.print("> ");
            reply = scanner.nextLine();
        }

        reply = reply.trim();

        if(reply.equals("exit")) System.exit(0);
        if(dictionary.containsKey(reply)) {
            dictionary.get(reply).execute();
            return true;
        }else {
            pipeout.println("Такой команды нет!");
            return false;
        }
    }
}
