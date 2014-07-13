package us.bpsm.treacle;

import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class NaiveTree implements Tree {
    CharSequence name;
    CharSequence content;
    List<Tree> children;

    NaiveTree(CharSequence name, CharSequence content, List<Tree> children) {
        this.name = name;
        this.content = content;
        this.children = children;
    }

    @Override
    public Iterator<Tree> iterator() {
        return children.iterator();
    }

    @Override
    public CharSequence name() {
        return name;
    }

    @Override
    public CharSequence content() {
        return content;
    }
}
