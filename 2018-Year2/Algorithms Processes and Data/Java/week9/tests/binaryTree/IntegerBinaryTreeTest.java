package binaryTree;

import arrayGenerator.generator.ArrayGenerator;
import arrayGenerator.generator.IntegerArrayGenerator;
import arrayGenerator.generator.ScopedArrayGenerator;
import arrayGenerator.scope.IntegerScope;
import arrayGenerator.scope.Scope;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-07
 */
class IntegerBinaryTreeTest<T extends Comparable<? super T>> extends BinaryTreeTest<Integer> {
    @Override
    public ArrayGenerator<Integer> getGenerator()
    {
        return new IntegerArrayGenerator(new IntegerScope());
    }

    public ScopedArrayGenerator<Integer> getGenerator(Scope<Integer> scope) {
        return new IntegerArrayGenerator(scope);
    }
}