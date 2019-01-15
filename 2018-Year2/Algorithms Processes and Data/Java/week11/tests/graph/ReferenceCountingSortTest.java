package graph;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-15
 */
class ReferenceCountingSortTest {
    ReferenceCountingSort<Integer> graph = new ReferenceCountingSort<>();

    private void createGraph(int size) throws GraphError {
        for(int i = 0; i < size; i++) {
            graph.add(i);
        }
    }

    @Test
    void test() throws GraphError {
        createGraph(10);
        graph.add(0,1);
        graph.add(0,5);
        graph.add(1,7);
        graph.add(3,2);
        graph.add(3,4);
        graph.add(3,8);
        graph.add(6,0);
        graph.add(6,1);
        graph.add(6,2);
        graph.add(8,4);
        graph.add(8,7);
        graph.add(9,4);
        assertEquals(graph.getSort(), Arrays.asList(3, 6, 0, 1, 2, 5, 8, 7, 9, 4));
    }

}