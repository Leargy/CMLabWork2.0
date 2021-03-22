package equationRW.writers;

import equationRW.writers.interfaces.EquationsWritable;

public class ConsoleWriter implements EquationsWritable {
    @Override
    public void write(String... args) {
        int length = args.length;
        if (length == 1) {
            System.out.print(args[0]);
        }else {
            System.out.print(args[0]+ " | ");
            for (int i = 1; i < length; i++) {
                System.out.print(args[i] + " | ");
            }
        }
        System.out.println();
    }
}
