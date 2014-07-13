package us.bpsm.treacle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class NaiveTreeSpace implements TreeSpace {
    @Override
    public Tree newTree(CharSequence name, CharSequence content, Iterable<Tree> children) {
        return new NaiveTree(name, content, copyToList(children));
    }

    static <E> List<E> copyToList(Iterable<E> items) {
        List<E> result = new ArrayList<E>();
        for (E e: items) {
            result.add(e);
        }
        return result;
    }
}
