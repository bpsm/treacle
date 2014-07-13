package us.bpsm.treacle;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class TreeTest {

    TreeSpace space;

    @Before
    public void initSpace() {
        //space = new CompactTreeSpace(new BlockedIntegers());
        //space = new CompactTreeSpace(new Integers());
        space = new NaiveTreeSpace();
    }

    Tree newTree(String name, String content, Tree ... children) {
        return space.newTree(name, content, Arrays.asList(children));
    }

    Tree newTree(String name, Tree ... children) {
        return space.newTree(name, "", Arrays.asList(children));
    }

    @Test
    public void testConstructSingletonTree() {
        Tree t = newTree("Hello", "World");
        assertEquals("Hello", t.name());
        assertEquals("World", t.content());
    }

    @Test
    public void testNodeWithOneChild() {
        Tree t = newTree("hello", newTree("leaf"));
        assertEquals("leaf", t.iterator().next().name());
        int n = 0;
        for (Tree c: t) {
            n++;
        }
        assertEquals(1, n);
    }

    Tree makeDeep(int d) {
        if (d == 0) {
            return newTree("leaf", ""+(int)(Math.random()*100));
        }
        return newTree("depth" + d, makeDeep(d-1), makeDeep(d-1), makeDeep(d-1));
    }

    public static int countNodes(Tree t) {
        int count = 1;
        for (Tree c: t) {
            count += countNodes(c);
        }
        return count;
    }

    @Test
    public void testDeepTree() {
        Runtime r = Runtime.getRuntime();
        for (int d = 5; d < 14; d++) {
            System.gc();
            long free = r.freeMemory();
            long total = r.totalMemory();
            long time = System.nanoTime();
            long used = total - free;
            Tree t = makeDeep(d);
            int n = countNodes(t);
            time = System.nanoTime() - time;
            System.gc();
            free = free - r.freeMemory();
            total = r.totalMemory() - total;
            used = (r.totalMemory() - r.freeMemory()) - used;
            assertEquals(countNodes(t), n);
            System.err.printf("%2d, ∂Free %8d KB, ∂Total %8d KB, ∂Used %8d KB, %6d ms, Nodes %8d%n",
                    d, free/1024, total/1024, used/1024, (int)(time * 1e-6), n);
        }
    }
}
