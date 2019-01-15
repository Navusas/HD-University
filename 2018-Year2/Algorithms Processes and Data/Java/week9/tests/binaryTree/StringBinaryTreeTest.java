package binaryTree;

import arrayGenerator.generator.ArrayGenerator;
import arrayGenerator.generator.ScopedArrayGenerator;
import arrayGenerator.generator.StringArrayGenerator;
import arrayGenerator.scope.Scope;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-14
 */
public class StringBinaryTreeTest<T extends Comparable<? super T>> extends BinaryTreeTest<String> {
    @Override
    public ScopedArrayGenerator<String> getGenerator(Scope<String> scope) {
        return new StringArrayGenerator(scope);
    }

    @Override
    public ArrayGenerator<String> getGenerator() {
        return new StringArrayGenerator();
    }
}
