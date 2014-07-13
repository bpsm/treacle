package us.bpsm.treacle;

import java.util.Iterator;

import static us.bpsm.treacle.CompactTreeSpace.*;

/**
 *
 */
class CompactTree implements Tree {
    final int id;
    final CompactTreeSpace space;

    CompactTree(int id, CompactTreeSpace space) {
        this.id = id;
        this.space = space;
    }

    @Override
    public Iterator<Tree> iterator() {
        final AbstractIntegers index = space.index;
        final int n = index.get(id + CHILD_COUNT_OFF);
        final int beg = id + FIRST_CHILD_OFF;
        final int end = beg + n;
        return new CompactTreeIterator(beg, end, space);
    }

    @Override
    public CharSequence name() {
        final AbstractIntegers index = space.index;
        return space.names.subSequence(
                index.get(id + NAME_BEG_OFF),
                index.get(id + NAME_END_OFF));
    }

    @Override
    public CharSequence content() {
        final AbstractIntegers index = space.index;
        return space.contents.subSequence(
                index.get(id + CONT_BEG_OFF),
                index.get(id + CONT_END_OFF));
    }
}
