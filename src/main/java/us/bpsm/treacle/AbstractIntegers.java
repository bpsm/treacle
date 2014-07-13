package us.bpsm.treacle;

/**
 *
 */
public abstract class AbstractIntegers {
    abstract int get(int i);

    abstract void set(int i, int v);

    abstract void add(int v);

    abstract int size();

    AbstractIntegers widen() {
        return this;
    }
}
