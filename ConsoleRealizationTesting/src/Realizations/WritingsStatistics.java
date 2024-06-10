package Realizations;

import Intefaces.Coder;
import Intefaces.Statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WritingsStatistics implements Statistics {
    private final Coder coder;
    private int totalCharsCount;
    private int totalWritingsCount;
    private final HashMap<LocalDate, Integer> writingsPerDay;
    private final String filePath;

    public WritingsStatistics(String filename, Coder coder) {
        totalCharsCount = 0;
        totalWritingsCount = 0;
        writingsPerDay = new HashMap<>();
        this.filePath = filename;
        this.coder = coder;
    }

    @Override
    public void recalculate(){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                String decryptedLine = coder.decrypt(line);
                String[] pair = decryptedLine.split("\\s+", 2);
                LocalDate date = LocalDate.parse(pair[0]);
                String writing = pair[1];

                totalCharsCount += writing.length();
                totalWritingsCount++;
                writingsPerDay.put(date, writingsPerDay.getOrDefault(date, 0) + 1);
            }
        } catch (IOException e) {
            System.out.println("ошибка при подсчете статистики");
        }
    }

    private LocalDate getMostActiveDay(){
        int maxWritingsPerDay = 0;
        LocalDate activeDay = null;

        for (Map.Entry<LocalDate, Integer> pair: writingsPerDay.entrySet()){
            if (pair.getValue() > maxWritingsPerDay){
                maxWritingsPerDay = pair.getValue();
                activeDay = pair.getKey();
            }
        }
        return activeDay;
    }

    @Override
    public String toString() {
        return "Статистика { " +
                "Суммарное число написанных символов=" + totalCharsCount +
                ", Суммарное число записей=" + totalWritingsCount +
                ", Самый активный день=" + getMostActiveDay() +
                ", Число записей в самый активный день=" + writingsPerDay.get(getMostActiveDay()) +
                " }";
    }
}
