package Realizations;

import Intefaces.Coder;
import Intefaces.DataWorker;

import java.io.*;
import java.time.LocalDate;

public class FileWorker<T> implements DataWorker<T> {
    private final String filePath;
    private final Coder coder;

    public FileWorker(String filePath, Coder coder) {
        this.filePath = filePath;
        this.coder = coder;
    }

    @Override
    public void write(T entry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(coder.encrypt(entry.toString()));
        } catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        }
        catch (IOException e) {
            System.out.println("ошибка при записи");
        }
    }

    @Override
    public void read() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String writing;
            while ((writing = reader.readLine()) != null)
                System.out.println(coder.decrypt(writing));
        } catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        }
        catch (IOException e) {
            System.out.println("ошибка при чтении");
        }
    }
    @Override
    public void readByDate(LocalDate inputDate){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            boolean dateFound = false;
            String line;
            while ((line = reader.readLine()) != null){
                String decryptedLine = coder.decrypt(line);
                String[] pair = decryptedLine.split("\\s+", 2);
                LocalDate date = LocalDate.parse(pair[0]);
                String writing = pair[1];

                if (inputDate.equals(date)){
                    System.out.println(writing);
                    dateFound = true;
                }
            }
            if (!dateFound) System.out.println("В этот день не было записей");
        } catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        }
        catch (IOException e) {
            System.out.println("ошибка при чтении");
        }
    }
}
