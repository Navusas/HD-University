package SingleLinkedList;

import SingleLinkedList.generator.ArrayGenerator;
import SingleLinkedList.generator.IntegerArrayGenerator;
import SingleLinkedList.generator.ScopedArrayGenerator;
import org.junit.jupiter.api.Test;
import SingleLinkedList.scope.IntegerScope;
import SingleLinkedList.scope.Scope;

class IntegerArrayGeneratorTest extends LinkedListTest<Integer> {

    @Override
    public ArrayGenerator<Integer> getGenerator()
    {
        return new IntegerArrayGenerator(new IntegerScope());
    }

    public ScopedArrayGenerator<Integer> getGenerator(Scope<Integer> scope) {
        return new IntegerArrayGenerator(scope);
    }

    @Test
    void testInRangeThreeToEightySeven() {
        testWithinRange(new IntegerScope(3,87),150);
    }

    @Test
    void testCoversZeroToNNineHundred() {
        testCoversRange(new IntegerScope(0,900));
    }
}