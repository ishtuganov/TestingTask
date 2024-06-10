package Intefaces;

import java.time.LocalDate;

public interface DataWorker<T> {
    public void write(T entry);
    public void read();
    public void readByDate(LocalDate date);
}
