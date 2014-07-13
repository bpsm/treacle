package us.bpsm.treacle;

/**
 *
 */
public class ShortBlock extends AbstractIntegers {
    short[] shorts;
    int size = 0;

    ShortBlock(int blockSize) {
        shorts = new short[blockSize];
    }

    @Override
    int get(int i) {
        return shorts[i];
    }

    @Override
    void set(int i, int v) {
        if (v < Short.MIN_VALUE || v > Short.MAX_VALUE) {
            throw new OverflowException();
        }
        shorts[i] = (short) v;
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

    @Override
    IntBlock widen() {
        IntBlock result = new IntBlock(this.shorts.length);
        for (int i = 0; i < this.shorts.length; i++) {
            result.ints[i] = this.shorts[i];
        }
        result.size = this.size;
        return result;
    }
}
