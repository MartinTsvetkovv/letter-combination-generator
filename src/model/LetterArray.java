package model;

import contract.StringGenerator;

import java.util.Random;

public class LetterArray extends StringArrayCreator implements StringGenerator {


    public LetterArray(String[] array) {
        super(array);
    }

    public StringBuilder generateAllFourLetterCombinations(String pref, StringBuilder sb, int num) {
        if (num == 0) {
            return sb.append(pref);
        }
        for (int i = 97; i <= 122; i++) {
            generateAllFourLetterCombinations(pref + (char) i, sb, num - 1);
        }
        return sb;
    }


    @Override
    public String[] randomStringGenerator(Random random, int stringNumber, int charNumber) {
        String[] result = new String[stringNumber];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringNumber; i++) {
            for (int j = 0; j < charNumber; j++) {
                int index = random.nextInt(this.array.length);
                sb.append(this.array[index]);
            }
            result[i] = sb.toString();
            sb.setLength(0);
        }

        return result;
    }

    @Override
    protected void fillArray(String[] array) {
        for (int i = 0; i < array.length; i++)
            array[i] = String.valueOf((char) (97 + i));
    }

    @Override
    public String[] getArray() {
        return this.array;
    }
}
