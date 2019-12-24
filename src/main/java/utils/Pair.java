package utils;

public class Pair<T> {
    T a;
    T b;

    public Pair(T a, T b) {
        this.a = a;
        this.b = b;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Pair))
            return false;

        Pair<T> otherPair = (Pair<T>) o;

        return otherPair.a.equals(a) && otherPair.b.equals(b)
                || otherPair.a.equals(b) && otherPair.b.equals(a);

    }

    public int hashCode() {
        return a.hashCode() + b.hashCode();
    }
}
