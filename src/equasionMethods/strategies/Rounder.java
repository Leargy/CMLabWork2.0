package equasionMethods.strategies;

public abstract class Rounder {

    public static Double round(int bound, Double number) {
        Double scale = Math.pow(10, bound);
        return  Math.ceil(number * scale) / scale;
    }
}
