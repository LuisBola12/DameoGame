package dameo.logic;

public class Pair <T,U> {
    private T key;
    private U value;
    public Pair(T myKey, U myValue) {
        this.key = myKey;
        this.value = myValue;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public U getValue() {
        return value;
    }

    public void setValue(U value) {
        this.value = value;
    }

    public boolean isInPair(T given_x, U given_y){
        if ((given_x.equals(key)) && (given_y.equals(value))){
            return true;
        }else {
            return false;
        }
    }
}