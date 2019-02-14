package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-14
 */
public class DepthFirstTraversal<T> extends AdjacencyGraph<T> implements Traversal<T> {

    private List<T> traversal = new ArrayList<T>(); // the traversal list

    @Override
    public List<T> traverse() throws GraphError {
        T node = getUnvisited(); // get first unvisited node
        traverseHelper(node); // start recursively visiting the nodes
        return traversal;
    }

    public List<T> traverse(T startPoint) throws GraphError {
        T node = getSpecificUnvisitedNode(startPoint); // get first unvisited node
        traverseHelper(node); // start recursively visiting the nodes
        return traversal;
    }

    /**
     * Recursively visit all nodes using Depth-First Traversal.
     *
     * @param next node to be visited
     * @throws GraphError if node is not in a graph
     */
    private void traverseHelper(T next) throws GraphError {
        if (next != null && !isVisited(next)) { // check if node exists and haven't been visited
            visitNode(next); // visit the node
            /* Recursively call same method, but this time get
             * first unvisited node from the one added*/
            traverseHelper(getUnvisitedNeighbours(next));
        } else { // if there is no connections from current node
            int i = traversal.size() - 1; // from end of list find if there is any unvisited nodes
            for (; i >= 0; i--) {
                // for each node check if there is unvisited nodes
                if (getUnvisitedNeighbours(traversal.get(i)) != null) {
                    // if there is unvisited node start this method from that node
                    traverseHelper(getUnvisitedNeighbours(traversal.get(i)));
                    break;
                }
            }
        }
    }

    /**
     * Check whether node (given value) has been visited previously
     *
     * @param value to check if its been visited
     * @return true if given node (value) been visited
     */
    private boolean isVisited(T value) {
        return traversal.contains(value);
    }

    /**
     * @return first unvisited node (element) in the graph
     */
    private T getUnvisited() {
        for (T node : getNodes()) {
            if (!isVisited(node)) {
                return node;
            }
        }
        return null;
    }

    /**
     * When reuested to start at specific node while doing Depth-First Traversal,
     * this method checks if that node haven't previously been used
     *
     * @param node which will be checked if unvisited
     * @return the same node, which was cast as parameter if node is unvisited
     * @throws GraphError if element is not in a graph
     */
    private T getSpecificUnvisitedNode(T node) throws GraphError {
        if (!contains(node)) throw new GraphError("The node does not exists in the graph");
        else if (isVisited(node)) throw new NullPointerException("This node has been visited before");
        else return node;
    }

    /**
     * Finds the first neighbour(edges) for requested element in graph.
     *
     * @param value which connection to their neighbours are going to be checked
     * @return node if there is any unvisited neighbours, null if all nodes has been visited previously
     * @throws GraphError if element does not exists in graph
     */
    private T getUnvisitedNeighbours(T value) throws GraphError {
        for (T node : getNeighbours(value)) { // for each node in neighbours of required root node
            if (!isVisited(node)) return node; // return the first unvisited node
        }
        return null; // return null
    }

    /**
     * Marks node as visited
     *
     * @param node to be visited
     */
    private void visitNode(T node) {
        if (isVisited(node)) return; // if node has been visited just return null
        traversal.add(node); // otherwise add node to the traversal list (mark as visited)
    }
}
