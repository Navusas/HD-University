package SingleLinkedList;

import SingleLinkedList.generator.ArrayGenerator;
import SingleLinkedList.generator.CharacterArrayGenerator;
import SingleLinkedList.generator.ScopedArrayGenerator;
import org.junit.jupiter.api.Test;
import SingleLinkedList.scope.CharacterScope;
import SingleLinkedList.scope.Scope;

class CharacterArrayGeneratorTest extends LinkedListTest<Character> {

    @Override
    public ScopedArrayGenerator<Character> getGenerator(Scope<Character> scope) {
        return new CharacterArrayGenerator(scope);
    }

    @Override
    public ArrayGenerator<Character> getGenerator() {
        return new CharacterArrayGenerator();
    }

    @Test
    void testInAlphabet()
    {
        testWithinRange(new CharacterScope(),150);
    }

    @Test
    void testCoversAlphabet() {
        testCoversRange(new CharacterScope());
    }

}