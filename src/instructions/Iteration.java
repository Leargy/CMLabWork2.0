package instructions;

import service.TotalCommander;

public class Iteration implements Command{
    private TotalCommander totalCommander;
    public Iteration(TotalCommander totalCommander) {
        this.totalCommander = totalCommander;
    }

    @Override
    public void execute() {
        totalCommander.iterativeMethodExecute();
    }

    public static final String NAME = "iterate_equation";
}
