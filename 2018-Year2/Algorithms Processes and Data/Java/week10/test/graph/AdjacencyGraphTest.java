package graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-14
 */
class AdjacencyGraphTest {
    private DepthFirstTraversal<Integer> graph = new DepthFirstTraversal<>();
    @Test
    void inClassTest() throws GraphError {
        // creating nodes
        createGraph(6);

        //adding edges(connections) to the graph
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(2,1);
        graph.addEdge(2,4);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(5,4);

        assertEquals(graph.traverse(), Arrays.asList(0,1,2,4,5,3));
    }
    @Test
    void testFourNodesSixEdges() throws GraphError {
        createGraph(4);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
        graph.addEdge(3,3);
        assertEquals(graph.traverse(2), Arrays.asList(2,0,1,3));
    }
    @Test
    void testFiveNodesNineEdges() throws GraphError {
        createGraph(6);
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);
        graph.addEdge(4, 1);
        graph.addEdge(4, 0);
        assertEquals(graph.traverse(), Arrays.asList(0,1,2,3,4,5));
    }
    private void createGraph(int size) throws GraphError {
        for(int i = 0; i < size; i++) {
            graph.add(i);
        }
    }
}