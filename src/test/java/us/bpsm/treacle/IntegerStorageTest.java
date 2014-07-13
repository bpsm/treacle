package us.bpsm.treacle;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public abstract class IntegerStorageTest {

    IntegerStorage ai;

    abstract IntegerStorage newIntegers();

    @Before
    public void initSpace() {
        ai = newIntegers();
    }

    @Test
    public void newIntegersHasSizeZero() {
        assertEquals(0, ai.size());
    }

    @Test
    public void storeOneByte() {
        ai.add(1);
        assertEquals(1, ai.get(0));
    }

    @Test
    public void storeOneShort() {
        ai.add(Short.MAX_VALUE);
        assertEquals(Short.MAX_VALUE,ai.get(0));
    }

    @Test
    public void storeOneInt() {
        ai.add(Short.MAX_VALUE+1);
        assertEquals(Short.MAX_VALUE+1,ai.get(0));
    }

    @Test
    public void storeManyNumbers() {
        for(int i = 0; i < Short.MAX_VALUE*2; i++) {
            ai.add(i);
        }
        for (int i = 0; i < Short.MAX_VALUE*2; i++) {
            assertEquals(i, ai.get(i));
        }
    }

    @Test
    public void storeSparseNumbers() {
        ai.set(0, 1);
        ai.set(10, 11);
        ai.set(100, 101);
        ai.set(1000, 1001);
        ai.set(10000, 10001);
        ai.set(100000, 100001);
        ai.set(1000000, 1000001);
        ai.set(10000000, 10000001);
        ai.set(100000000, 100000001);

        assertEquals(1, ai.get(0));
        assertEquals(100000001, ai.get(100000000));

    }


}
