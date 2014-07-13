package us.bpsm.treacle;

/**
 *
 */
public interface Tree extends Iterable<Tree> {
    CharSequence name();
    CharSequence content();
}
