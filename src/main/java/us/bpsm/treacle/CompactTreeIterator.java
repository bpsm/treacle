package us.bpsm.treacle;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 */
public class CompactTreeIterator implements Iterator<Tree> {
    int i;
    final int end;
    final CompactTreeSpace space;

    CompactTreeIterator(int beg, int end, CompactTreeSpace space) {
        this.i = beg;
        this.end = end;
        this.space = space;
    }

    @Override
    public boolean hasNext() {
        return (i < end);
    }

    @Override
    public Tree next() {
        if (i >= end) {
            throw new NoSuchElementException();
        }
        int id = space.index.get(i++);
        if (id >= 0) {
            return new CompactTree(id, space);
        } else {
            return space.foreign.get(Math.abs(id)-1);
        }
    }
}
