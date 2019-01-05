package linkedList.list;

import arrayGenerator.ScopedArrayGeneratorTest;
import arrayGenerator.generator.*;
import arrayGenerator.scope.IntegerScope;
import arrayGenerator.scope.Scope;
import org.junit.jupiter.api.Test;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-04
 */
class SingleLinkListTest extends ScopedArrayGeneratorTest<Integer> {
    @Override
    public ArrayGenerator<Integer> getGenerator()
    {
        return new IntegerArrayGenerator(new IntegerScope());
    }
    public ScopedArrayGenerator<Integer> getGenerator(Scope<Integer> scope) {
        return new IntegerArrayGenerator(scope);
    }
    @Test
    void testInSingletonRangeFour() throws ListAccessError {
        ArrayHandler<Integer> handler = new ArrayHandler<>();
        Integer[] array = handler.getIntegerArray(90000);
        SingleLinkList<Integer> list = handler.copyFrom(array);
        System.out.println(System.currentTimeMillis());
        long current = System.currentTimeMillis();
        int value = list.get(89000);
        System.out.println(System.currentTimeMillis());

        System.out.println("Time: " + (System.currentTimeMillis()-current));
        current = System.currentTimeMillis();
        value = array[89000];
        System.out.println("Time: " + (System.currentTimeMillis()-current));
    }
}