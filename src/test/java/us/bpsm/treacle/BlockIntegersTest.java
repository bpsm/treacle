package us.bpsm.treacle;

/**
 *
 */
public class BlockIntegersTest extends AbstractIntegersTest {

    @Override
    AbstractIntegers newIntegers() {
        return new BlockedIntegers();
    }
}
