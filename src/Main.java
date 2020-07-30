import contract.Printer;
import model.LetterArray;

import java.io.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        long start = System.nanoTime();
        Printer printer = new ConsolePrinter();
        String[] letters = new String[26];
        StringBuilder sb = new StringBuilder();
        LetterArray letterArray = new LetterArray(letters);
        FileManager fileWriter = new FileManager();

        Random random = new Random();
        File file = new File("src/resource/RandomCharacters.txt");

        StringBuilder allCombinations = letterArray.generateAllFourLetterCombinations("", sb, 4);

         fileWriter.writeIntoFile(allCombinations, "src/resource/combinations");
        //String[] randomLetters = letterArray.randomStringGenerator(random, 3, 3);
        //fileWriter.randomSizeFileGenerator(file, letterArray.getArray(), random, 1.0);


        //WordCounterExecutor wordCounterExecutor = new WordCounterExecutor(printer);
        //wordCounterExecutor.executeWordCount(randomLetters, "src/resource/RandomCharacters.txt");


        long time = System.nanoTime() - start;
        System.out.printf("Took %.3f seconds to generate", time / 1e9);


    }
}
