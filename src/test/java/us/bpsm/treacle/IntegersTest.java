package us.bpsm.treacle;

/**
 *
 */
public class IntegersTest extends IntegerStorageTest {

    @Override
    IntegerStorage newIntegers() {
        return new Integers();
    }
}
