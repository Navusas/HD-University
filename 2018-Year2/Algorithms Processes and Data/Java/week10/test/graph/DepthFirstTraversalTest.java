package graph;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-14
 */
class DepthFirstTraversalTest {
    private DepthFirstTraversal<Integer> graph = new DepthFirstTraversal<>();

    @Test
    void inClassTest() throws GraphError {
        // creating nodes
        createGraph(6);

        //adding edges(connections) to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 1);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 4);

        assertEquals(graph.traverse(), Arrays.asList(0, 1, 2, 4, 5, 3));
    }

    @Test
    void testFourNodesSixEdges() throws GraphError {
        createGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
        assertEquals(graph.traverse(2), Arrays.asList(2, 0, 1, 3));
    }

    @Test
    void testSixNodesNineEdges() throws GraphError {
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
        assertEquals(graph.traverse(), Arrays.asList(0, 1, 2, 3, 4, 5));
    }

    @Test
    void testSize0() {
        assertEquals(graph.getNoOfNodes(), 0);
    }

    @Test
    void testAccessingOutOfBoundsElement() throws GraphError {
        createGraph(10);
        assertThrows(GraphError.class, () -> {
            graph.getNeighbours(20);
        });
    }

    @Test
    void testRemoving_Size5182() throws GraphError {
        createGraph(5182);
        graph.remove(2432);
        assertFalse(graph.contains(2432));
    }

    @Test
    void testContains_size3000() throws GraphError {
        createGraph(3000);
        assertTrue(graph.contains(1));
    }

    @Test
    void testNodesSizeThousand() throws GraphError {
        createGraph(1000);
        assertEquals(graph.getNoOfNodes(), 1000);
    }

    @Test
    void testAdd() throws GraphError {
        createGraph(1);
        graph.add(2);
        assertEquals(graph.getNoOfNodes(), 2);
    }

    private void createGraph(int size) throws GraphError {
        for (int i = 0; i < size; i++) {
            graph.add(i);
        }
    }
}