package us.bpsm.treacle;

/**
 *
 */
public interface TreeSpace {
    public Tree newTree(CharSequence name, CharSequence content, Iterable<Tree> children);
}
