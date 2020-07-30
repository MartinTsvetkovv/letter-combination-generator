import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class FileManager {

    public void writeIntoFile(StringBuilder stringBuilder, String filePath) {
        try {
            Files.writeString(Path.of(filePath), stringBuilder.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void randomSizeFileGenerator(File file, String[] array, Random random, double fileSize) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)), false);
        int counter = 0;
        while (true) {

            int i = random.nextInt(array.length);
            String ch = array[i];
            printWriter.write(ch);

            if (counter++ == 20000) {
                //System.out.printf("Size: %.3f GB%n", file.length() / 1e9);
                if (file.length() >= fileSize * 1e9) {
                    printWriter.close();
                    break;
                } else {
                    counter = 0;
                }

            }
        }
    }

}
