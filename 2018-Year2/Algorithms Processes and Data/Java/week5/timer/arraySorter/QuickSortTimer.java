package arraySorter;

import timer.Timer;

public abstract class QuickSortTimer<T extends Comparable<? super T>> extends QuickSort<T> implements Timer {

    private T[] array; // this is the array that will be sorted

    void setArray(T[] array) {
        this.array = array;
    }

    @Override
    public void timedMethod() {
        sort(array);
    }

    @Override
    public long getMaximumRuntime() {
        return Long.MAX_VALUE;
    }

    @Override
    public int getMinimumTaskSize() {
        return 1;
    }

    @Override
    public int getMaximumTaskSize() {
        return 50000;}

    @Override
    public int getRunSetSize() {
        return 100;
    }
}
