package us.bpsm.treacle;

import java.util.Arrays;

class Integers extends AbstractIntegers {
    private static final int INITIAL_BLOCK_CAPACITY = 16;
    private static final int BLOCK_SIZE = 1024;
    int[][] blocks;
    int size;

    Integers() {
        blocks = new int[INITIAL_BLOCK_CAPACITY][];
        blocks[0] = new int[BLOCK_SIZE];
        size = 0;
    }

    @Override
    int get(int i) {
        try {
            final int hi = i / BLOCK_SIZE;
            final int lo = i % BLOCK_SIZE;
            return blocks[hi][lo];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        } catch (NullPointerException e) {
            return 0;
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
            blocks[hi] = new int[BLOCK_SIZE];
        }
    }

    @Override
    void set(int i, int v) {
        final int hi = i / BLOCK_SIZE;
        final int lo = i % BLOCK_SIZE;
        try {
            blocks[hi][lo] = v;
        } catch (ArrayIndexOutOfBoundsException e) {
            expand(hi);
            blocks[hi][lo] = v;
        } catch (NullPointerException e) {
            expand(hi);
            blocks[hi][lo] = v;
        }
    }

    @Override
    void add(int v) {
        set(size++, v);
    }

    @Override
    int size() {
        return size;
    }

}
