package binaryTree;

import arrayGenerator.generator.ArrayGenerator;
import arrayGenerator.generator.CharacterArrayGenerator;
import arrayGenerator.generator.ScopedArrayGenerator;
import arrayGenerator.scope.Scope;
import org.junit.jupiter.api.Test;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-14
 */
public class CharacterBinaryTreeTest<T extends Comparable<? super T>> extends BinaryTreeTest<Character> {
    @Override
    public ScopedArrayGenerator<Character> getGenerator(Scope<Character> scope) {
        return new CharacterArrayGenerator(scope);
    }

    @Override
    public ArrayGenerator<Character> getGenerator() {
        return new CharacterArrayGenerator();
    }

}
