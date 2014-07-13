package us.bpsm.treacle;

import java.util.Arrays;

class BlockedIntegers extends AbstractIntegers {
    private static final int INITIAL_BLOCK_CAPACITY = 16;
    private static final int BLOCK_SIZE = 1024;
    AbstractIntegers[] blocks;
    int blockindex;

    BlockedIntegers() {
        blocks = new AbstractIntegers[INITIAL_BLOCK_CAPACITY];
        blocks[0] = new ByteBlock(BLOCK_SIZE);
        blockindex = 0;
    }

    @Override
    int get(int i) {
        try {
            final int hi = i / BLOCK_SIZE;
            final int lo = i % BLOCK_SIZE;
            return blocks[hi].get(lo);
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    void set(int i, int v) {
        final int hi = i / BLOCK_SIZE;
        final int lo = i % BLOCK_SIZE;
        try {
            blocks[hi].set(lo, v);
        } catch (ArrayIndexOutOfBoundsException e) {
            expand(hi);
            set(i, v);
        } catch (NullPointerException e) {
            expand(hi);
            set(i, v);
        } catch (OverflowException e) {
            blocks[hi] = blocks[hi].widen();
            set(i, v);
        }
    }

    void expand(int hi) {
        if (blocks.length <= hi) {
            int cap = blocks.length;
            do {
                cap *= 2;
            } while (cap <= hi);
            blocks = Arrays.copyOf(blocks, cap);
        }
        if (blocks[hi] == null) {
            blocks[hi] = new ByteBlock(BLOCK_SIZE);
        }
    }

    @Override
    void add(int v) {
        try {
            blocks[blockindex].add(v);
        } catch (ArrayIndexOutOfBoundsException e) {
            blockindex++;
            expand(blockindex);
            add(v);
        } catch (OverflowException e) {
            blocks[blockindex] = blocks[blockindex].widen();
            add(v);
        }
    }

    @Override
    int size() {
        return blocks[blockindex].size()
                + blockindex * BLOCK_SIZE;
    }

}
