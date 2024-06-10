import Intefaces.Coder;
import Intefaces.DataWorker;
import Intefaces.Statistics;
import Realizations.Encoder;
import Realizations.Entry;
import Realizations.FileWorker;
import Realizations.WritingsStatistics;

public class Main {
    public static void main(String[] args) {
        String filepath = "data.txt";
        Coder encoder = new Encoder();
        DataWorker<Entry> fileWorker = new FileWorker<>(filepath, encoder);
        Statistics writingsStatistics = new WritingsStatistics(filepath, encoder);
        Manager manager = new Manager(fileWorker, writingsStatistics);

        manager.run();
    }
}