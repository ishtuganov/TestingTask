import Intefaces.DataWorker;
import Intefaces.Statistics;
import Realizations.Entry;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Manager {
    private final Scanner scanner = new Scanner(System.in);
    private final DataWorker<Entry> dataWorker;
    private final Statistics statistics;

    public Manager(DataWorker<Entry> dataWorker, Statistics statistics) {
        this.dataWorker = dataWorker;
        this.statistics = statistics;
    }

    public void run(){
        while (true) {
            System.out.println("Введите команду (#read, #write, #statistics, #search): ");
            String action = scanner.nextLine().trim();
            manageAction(action);
        }
    }

    private void manageAction(String action){
        switch (action) {
            case "#read" -> dataWorker.read();
            case "#write" -> {
                System.out.print("Введите ваши планы на сегодня: ");
                String writing = scanner.nextLine();
                dataWorker.write(new Entry(writing));
            }
            case "#statistics" -> {
                statistics.recalculate();
                System.out.println(statistics);
            }
            case "#search" -> {
                System.out.print("Введите дату в формате yyyy-MM-dd (2020-01-31): ");
                try {
                    LocalDate inputDate = LocalDate.parse(scanner.nextLine());
                    dataWorker.readByDate(inputDate);
                } catch (DateTimeException e){
                    System.out.println("Неправильный формат даты");
                }
            }
            default -> System.out.println("неизвестная команда");
        }
    }
}
