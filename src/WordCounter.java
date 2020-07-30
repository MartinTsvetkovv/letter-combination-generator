import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter implements Runnable {

    private final Map<String, Integer> counters;
    private final Queue<String> dataQueue;
    private final String[] arrayGetter;
    private int counter;

    public WordCounter(String[] array, Map<String, Integer> counters, Queue<String> dataQueue) {
        this.counters = counters;
        this.dataQueue = dataQueue;
        this.arrayGetter = array;
        this.setCounter(counter);
    }

    @Override
    public void run() {
        while (!dataQueue.isEmpty()) {
            String line = dataQueue.poll();
            for (String s : arrayGetter) {
                Pattern pattern = Pattern.compile(s);
                if (line != null) {
                    Matcher matcher = pattern.matcher(line);
                    this.setCounter(0);
                    findMatches(matcher);
                    counters.put(pattern.toString(), counter);
                }
            }
        }
    }


    public synchronized void getCounter() {
        this.counter++;
    }

    public synchronized void setCounter(int counter) {
        this.counter = counter;
    }

    private void findMatches(Matcher matcher) {
        while (matcher.find()) {
            this.getCounter();
        }
    }
}
