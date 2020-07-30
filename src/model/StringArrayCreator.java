package model;

public abstract class StringArrayCreator {
    protected final String[] array;

    public StringArrayCreator(String[] array) {
        this.array = array;
        this.fillArray(array);
    }


    protected abstract void fillArray(String[] array);

    public abstract String[] getArray();

}
