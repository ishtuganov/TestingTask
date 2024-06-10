package Realizations;

import java.time.LocalDate;

public class Entry {
    private final String writing;
    private final LocalDate date;
    public Entry(String writing) {
        this.writing = writing;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return date + " " + writing + "\n";
    }
}
