package instructions;

import service.TotalCommander;

public class Secant implements Command{
    private TotalCommander totalCommander;
    public Secant(TotalCommander totalCommander) {
        this.totalCommander = totalCommander;
    }

    @Override
    public void execute() {
        totalCommander.secantMethodExecute();
    }

    public static final String NAME = "secant_equation";
}
