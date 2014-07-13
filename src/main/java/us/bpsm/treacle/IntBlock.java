package us.bpsm.treacle;

/**
 *
 */
public class IntBlock extends AbstractIntegers {
    int[] ints;
    int size = 0;

    IntBlock(int blockSize) {
        ints = new int[blockSize];
    }

    @Override
    int get(int i) {
        return ints[i];
    }

    @Override
    void set(int i, int v) {
        ints[i] = v;
    }

    @Override
    void add(int v) {
        set(size, v);
        size++;
    }

    @Override
    int size() {
        return size;
    }
}
