package linkedList.list;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-05
 */
public abstract class Comparison<T extends Comparable<? extends T>> {
    ArrayHandler<T> handler = new ArrayHandler<T>();

    Comparison() {

    }
    void compare(int size, int index) {

    }
    void createArray(int size) throws ListAccessError {
        ArrayHandler<Integer> handler = new ArrayHandler<Integer>();
        Integer[] array = handler.getIntegerArray(20000);
        SingleLinkList list = handler.copyFrom(array);
    }
}
