import contract.CounterExecutor;
import contract.Printer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WordCounterExecutor implements CounterExecutor {

    private final Printer printer;

    public WordCounterExecutor(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void executeWordCount(String[] array, String fileName) throws FileNotFoundException, InterruptedException {
        Map<String, Integer> counters = new HashMap<>();
        Queue<String> dataQueue = new ConcurrentLinkedQueue<>();
        new Thread() {
            final BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = null;

            @Override
            public void run() {

                try {
                    while ((line = br.readLine()) != null) {
                        dataQueue.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }.start();

        while (dataQueue.isEmpty()) {
            // Wait for the thread to start writing into the queue
            Thread.sleep(10);
        }
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            es.execute(new WordCounter(array, counters, dataQueue));
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.MINUTES);

        this.printer.print("Word Count:\n" + counters);

    }


}
