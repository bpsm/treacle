package us.bpsm.treacle;

/**
 *
 */
public class ByteBlock extends AbstractIntegers {
    byte[] bytes;
    int size = 0;

    ByteBlock(int blockSize) {
        bytes = new byte[blockSize];
    }

    @Override
    int get(int i) {
        return bytes[i];
    }

    @Override
    void set(int i, int v) {
        if (v < Byte.MIN_VALUE || v > Byte.MAX_VALUE) {
            throw new OverflowException();
        }
        bytes[i] = (byte) v;
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
    ShortBlock widen() {
        ShortBlock result = new ShortBlock(this.bytes.length);
        for (int i = 0; i < this.bytes.length; i++) {
            result.shorts[i] = this.bytes[i];
        }
        result.size = this.size;
        return result;
    }
}
