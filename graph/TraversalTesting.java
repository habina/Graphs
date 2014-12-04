package graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

import org.junit.Test;

/** Unit tests for the Traversal class.
 *  @author Dasheng Chen
 */
public class TraversalTesting{

    private class DFSClient extends Traversal {
        /** A constructor. */
        protected DFSClient(Graph G, Queue<Integer> fringe) {
            super(G, fringe);
        }

        @Override
        protected boolean visit(int v) {
            
            return false;
        }
        
        /** Vertex that visited. */
        private HashSet<Integer> _visited;

    }
    
    private class BFSClient extends Traversal {
        /** A constructor. */
        protected BFSClient(Graph G, Queue<Integer> fringe) {
            super(G, fringe);
        }
        
        @Override
        protected boolean visit(int v) {
            return false;
        }
        
    }

    @Test
    public void TestDFSTraversalClient() {
        DirectedGraph g = new DirectedGraph();
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
        // perform LIFO, like stack
        ArrayDeque<Integer> fringe = new ArrayDeque<Integer>();
        DFSClient dfsClient = new DFSClient(g, fringe);
        dfsClient.traverse(5);
        
    }

}
