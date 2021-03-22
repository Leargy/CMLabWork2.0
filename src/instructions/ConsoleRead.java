package instructions;

import service.TotalCommander;

public class ConsoleRead implements Command {
    private TotalCommander totalCommander;

    public ConsoleRead(TotalCommander totalCommander) {
        this.totalCommander = totalCommander;
    }

    @Override
    public void execute() {
        totalCommander.readMatrixFromConsole();
    }

    public static String NAME = "read_console";
}
