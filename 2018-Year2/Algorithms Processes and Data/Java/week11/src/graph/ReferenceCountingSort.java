package graph;

import java.util.*;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-14
 */
public class ReferenceCountingSort<T> extends AdjacencyGraph<T> implements TopologicalSort<T> {

    private HashMap<T, Integer> referenceCount = new HashMap<>();
    private List<T> sort = new ArrayList<>();
    private Set<T> visited = new HashSet<>();

    @Override
    public List<T> getSort() throws GraphError {
        setUpReferenceCount(); // initialize and get reference count
        T node;
        while ((node = findLowestReferenceCount()) != null) {
            visitNode(node); // visit all nodes
        }
        return sort; // topologically sorted list
    }

    /**
     * Checks if provided node has been visited previously
     *
     * @param node to be checked
     * @return true if node has been visited
     */
    private boolean isVisited(T node) {
        for (T value : visited) {
            if (value == node) return true;
        }
        return false;
    }

    /**
     * Checks if given node has been visited previously and
     * if not - adds the element to the sort list and marks as visited.
     *
     * @param node to be checked if visited and added to list
     * @throws GraphError if element does not exists in graph
     */
    private void visitNode(T node) throws GraphError {
        if (isVisited(node)) return; // exit if it has been visited previously
        else {
            visited.add(node); // mark as visited
            sort.add(node); // add to Topological list
            for (T neighbour : getNeighbours(node)) {
                decreaseReferenceCount(neighbour); // decrement all given node neighbours reference count
            }
        }

    }

    /**
     * Count 'Reference count' for each node storing its values
     * in HashMap, where:
     *  *   Key         -   Node
     *  *   Reference   -   Reference count(how many edges connection the node has
     *
     * @throws GraphError if node does not exists
     */
    private void setUpReferenceCount() throws GraphError {
        initializeHashMap(); // initialize reference count variable
        for (T root : getNodes()) { // for each node
            for (T neighbour : getNeighbours(root)) { // get every neighbour
                increaseReferenceCount(neighbour); // increase its reference number FOR the root node
            }
        }
    }

    /**
     * Finds the next predecessors (node having 0 reference count)
     *
     * @return predecessor (node having 0 reference count)
     */
    private T findLowestReferenceCount() {
        for (T element : referenceCount.keySet()) { // for each node
            if (!isVisited(element) && referenceCount.get(element) == 0) { // check if visited ...
                return element; // ... and if there are connections to that element anymore
                // return the element if true
            }
        }
        return null; // return null (terminate the doSort()
    }

    /**
     * Store all nodes as entries and 0's as keys
     */
    private void initializeHashMap() {
        for (T node : getNodes()) {
            referenceCount.put(node, 0);
        }
    }

    /**
     * Increases Reference Count of the given node by 1
     *
     * @param node which reference count will be increased
     */
    private void increaseReferenceCount(T node) {
        referenceCount.replace(node, (referenceCount.get(node) + 1));
    }
    /**
     * Decreases Reference Count of the given node by 1
     *
     * @param node which reference count will be decreased
     */
    private void decreaseReferenceCount(T node) {
        if (referenceCount.get(node) > 0) // to avoid negative numbers
            referenceCount.replace(node, (referenceCount.get(node) - 1));
    }

    /**
     * Neatly reference counts output to screen
     */
    private void referenceToString() {
        for (T element : referenceCount.keySet()) {
            System.out.print(referenceCount.get(element) + " ");
        }
    }
}

