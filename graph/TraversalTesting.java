package graph;

import java.util.Queue;

import org.junit.Test;

/** Unit tests for the Traversal class.
 *  @author Dasheng Chen
 */
public class TraversalTesting {
    @Test
    public void testClear() {
        Graph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(5, 4);
        g.add(5, 3);
        g.add(4, 1);
        g.add(3, 2);
        g.add(1, 5);
//        ArrayQueue<Integer> fringe = new ArrayQueue<Integer>();
        
    }

}
