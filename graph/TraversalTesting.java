package graph;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** Unit tests for the Traversal class.
 *  @author Dasheng Chen
 */
public class TraversalTesting{

    private class DFSClient extends DepthFirstTraversal {
        /** A constructor. */
        protected DFSClient(Graph G) {
            super(G);
            _record = new ArrayList<Integer>();
        }

        @Override
        protected boolean visit(int v) {
            _record.add(v);
            return true;
        }

        public ArrayList<Integer> getRecord() {
            return _record;
        }
        
        /** Visited record. */
        private ArrayList<Integer> _record;
    }
    
    private class BFSClient extends BreadthFirstTraversal {
        /** A constructor. */
        protected BFSClient(Graph G) {
            super(G);
            _record = new ArrayList<Integer>();
        }

        @Override
        protected boolean visit(int v) {
            _record.add(v);
            return true;
        }

        public ArrayList<Integer> getRecord() {
            return _record;
        }
        
        /** Visited record. */
        private ArrayList<Integer> _record;
    }

    private class DFSPostClient extends DepthFirstTraversal {

        protected DFSPostClient(Graph G) {
            super(G);
            _record = new ArrayList<Integer>();
        }
        
        @Override
        protected boolean postVisit(int v) {
            _record.add(v);
            return true;
        }
        
        @Override
        protected boolean shouldPostVisit(int v) {
            return true;
        }
        
        public ArrayList<Integer> getRecord() {
            return _record;
        }
        
        /** Visited record. */
        private ArrayList<Integer> _record;
    }
    
    @Test
    public void TestClientTraversal() {
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
        DFSClient dfsClient = new DFSClient(g);
        BFSClient bfsClient = new BFSClient(g);
        DFSPostClient dfsPostClient = new DFSPostClient(g);
        dfsClient.traverse(5);
        bfsClient.traverse(5);
        dfsPostClient.traverse(5);
        ArrayList<Integer> dfsVisited = dfsClient.getRecord();
        ArrayList<Integer> dfsExpected = new ArrayList<Integer>();
        dfsExpected.add(5);
        dfsExpected.add(4);
        dfsExpected.add(1);
        dfsExpected.add(3);
        dfsExpected.add(2);
        ArrayList<Integer> bfsVisited = bfsClient.getRecord();
        ArrayList<Integer> bfsExpected = new ArrayList<Integer>();
        bfsExpected.add(5);
        bfsExpected.add(4);
        bfsExpected.add(3);
        bfsExpected.add(1);
        bfsExpected.add(2);
        ArrayList<Integer> dfsPostVisited = dfsPostClient.getRecord();
        ArrayList<Integer> dfsPostExpected = new ArrayList<Integer>();
        dfsPostExpected.add(1);
        dfsPostExpected.add(4);
        dfsPostExpected.add(2);
        dfsPostExpected.add(3);
        dfsPostExpected.add(5);
        assertEquals(true, dfsVisited.equals(dfsExpected));
        assertEquals(true, bfsVisited.equals(bfsExpected));
        assertEquals(true, dfsPostVisited.equals(dfsPostExpected));
    }
}
