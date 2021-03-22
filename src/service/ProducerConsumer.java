package service;

import matrixMethods.customExceptions.*;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumer <T> {
    private static final int BUFFER_MAX_SIZE = 42;
    private List<T> buffer = new LinkedList<>();

    public void produce(T value) throws NonCompliaceException {
        if (buffer.size() == BUFFER_MAX_SIZE) throw new NonCompliaceException("Буффер переполнен");
        buffer.add(value);
    }

    public T consume() throws NonCompliaceException {
        if (buffer.size() == 0) throw new NonCompliaceException("Нет уравнения для решения!");

        T result = buffer.remove(0);

        return result;
    }
}
