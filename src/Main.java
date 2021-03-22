import instructions.*;
import service.ConsolePrompter;
import service.Invoker;
import service.TotalCommander;


public class Main {
//    private static TotalCommander totalCommander = new TotalCommander();
//    private static Invoker invoker = new ConsolePrompter(System.out, System.in);
//
//    static {
//        invoker.setCommand(FileRead.NAME, new FileRead(totalCommander));
//        invoker.setCommand(ConsoleRead.NAME, new ConsoleRead(totalCommander));
//    }


    public static void main(String[] args) {
        TotalCommander totalCommander = new TotalCommander();
        Invoker invoker = new ConsolePrompter(System.out, System.in);

        invoker.setCommand(FileRead.NAME, new FileRead(totalCommander));
        invoker.setCommand(ConsoleRead.NAME, new ConsoleRead(totalCommander));
        invoker.setCommand(Iteration.NAME, new Iteration(totalCommander));
        invoker.setCommand(Secant.NAME, new Secant(totalCommander));
        invoker.setCommand(Binary.NAME, new Binary(totalCommander));

        System.out.println("Доступные команды: \n" + FileRead.NAME + ", "
                + ConsoleRead.NAME + ", " + Iteration.NAME + ", "
                + Secant.NAME + ", " + Binary.NAME
                + ", exit\n");

        System.out.println("Шаблон заполнения файла и/или консольного ввода для работы с матрицами:");

        System.out.println("n e M");
        System.out.println("X11 X12 Xn B1\nXn1 Xn2 Xnn Bn");

        while (true) {
            invoker.scan();
//            while (invoker.scan());
        }
    }
}