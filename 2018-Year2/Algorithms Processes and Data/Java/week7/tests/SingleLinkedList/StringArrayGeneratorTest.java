package SingleLinkedList;

import SingleLinkedList.generator.ArrayGenerator;
import SingleLinkedList.generator.ScopedArrayGenerator;
import SingleLinkedList.generator.StringArrayGenerator;
import org.junit.jupiter.api.Test;
import SingleLinkedList.scope.Scope;
import SingleLinkedList.scope.StringScope;

import java.util.HashSet;
import java.util.Set;

class StringArrayGeneratorTest extends LinkedListTest<String> {

    @Override
    public ScopedArrayGenerator<String> getGenerator(Scope<String> scope) {
        return new StringArrayGenerator(scope);
    }

    @Override
    public ArrayGenerator<String> getGenerator() {
        return new StringArrayGenerator();
    }

    @Test
    void testInRangeDogCatMouse() {
        Set<String> alphabet = new HashSet<String>();
        alphabet.add("Dog");
        alphabet.add("Cat");
        alphabet.add("Mouse");
        testWithinRange(new StringScope(alphabet),150);
    }

    @Test
    void testInSingletonRangeHouse()
    {
        Set<String> alphabet = new HashSet<String>();
        alphabet.add("House");
        testWithinRange(new StringScope(alphabet),10);
    }

    @Test
    void testInAlphabet()
    {
        testWithinRange(new StringScope(),150);
    }

    @Test
    void testCoversAlphabet()
    {
        testCoversRange(new StringScope());
    }

    @Test
    void testCoversSingletonHuddersfield()
    {
        Set<String> alphabet = new HashSet<String>();
        alphabet.add("Huddersfield");
        testCoversRange(new StringScope(alphabet));
    }
}