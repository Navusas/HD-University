package linkedList.list;

import arrayGenerator.generator.CharacterArrayGenerator;
import arrayGenerator.generator.IntegerArrayGenerator;
import arrayGenerator.generator.StringArrayGenerator;
import arrayGenerator.scope.CharacterScope;
import arrayGenerator.scope.IntegerScope;
import arrayGenerator.scope.StringScope;

import java.util.Set;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-05
 */
public class ArrayHandler<T> {
    public ArrayHandler(){

    }
    SingleLinkList<T> copyFrom(T[] array) throws ListAccessError {
        SingleLinkList<T> list = new SingleLinkList<T>();
        for(int i = 0; i < array.length; i++) {
            list.add(i,array[i]);
        }
        return list;
    }
    Integer[] getIntegerArray(int size) {
        return new IntegerArrayGenerator(new IntegerScope(0,size)).getArray(size);
    }
    Integer[] getIntegerArray(int from, int to, int size) {
        return new IntegerArrayGenerator(new IntegerScope(from,to)).getArray(size);
    }
    Character[] getCharacterArray(int size) {
        return new CharacterArrayGenerator(new CharacterScope()).getArray(size);
    }
    Integer[] getCharacterArray(int range, int size) {
        return new IntegerArrayGenerator(new IntegerScope(range)).getArray(size);
    }
    String[] getStringArray(int size) {
        return new StringArrayGenerator(new StringScope()).getArray(size);
    }
    String[] getStringArray(Set<String> alphabetic, int size) {
        return new StringArrayGenerator(new StringScope(alphabetic)).getArray(size);
    }
}
