package contract;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;

public interface CounterExecutor{
    void executeWordCount(String[] array, String fileName) throws FileNotFoundException, InterruptedException;
}
