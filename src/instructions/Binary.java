package instructions;

import service.TotalCommander;

public class Binary implements Command{
    private TotalCommander totalCommander;
    public Binary(TotalCommander totalCommander) {
        this.totalCommander = totalCommander;
    }

    @Override
    public void execute() {
        totalCommander.binMethodExecute();
    }

    public static final String NAME = "binary_equation";
}
