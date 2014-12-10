package graph;

import org.junit.Test;
import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author Dasheng Chen
 */
public class GraphTesting {

    @Test
    public void testAdd() {
        DirectedGraph g = new DirectedGraph();
        assertEquals(1, g.add());
        assertEquals(2, g.add());
        assertEquals(3, g.add());
        assertEquals(1, g.add(1, 2));
        assertEquals(1, g.add(1, 2));
        assertEquals(2, g.add(2, 3));
        assertEquals(4, g.add());
        UndirectedGraph uG = new UndirectedGraph();
        assertEquals(1, uG.add());
        assertEquals(2, uG.add());
        assertEquals(3, uG.add());
        assertEquals(1, uG.add(1, 2));
        assertEquals(1, uG.add(1, 2));
        assertEquals(2, uG.add(2, 3));
        assertEquals(4, uG.add());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChecktMyVertex1() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.checkMyVertex(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChecktMyVertex2() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.add();
        dGraph.checkMyVertex(1);
        dGraph.checkMyVertex(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChecktMyVertex3() {
        UndirectedGraph uDGraph = new UndirectedGraph();
        uDGraph.checkMyVertex(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChecktMyVertex4() {
        UndirectedGraph uDGraph = new UndirectedGraph();
        uDGraph.add();
        uDGraph.checkMyVertex(1);
        uDGraph.checkMyVertex(2);
    }

    @Test
    public void testContains() {
        DirectedGraph g = new DirectedGraph();
        assertEquals(1, g.add());
        assertEquals(true, g.contains(1));
        assertEquals(false, g.contains(2));
        assertEquals(2, g.add());
        assertEquals(3, g.add());
        assertEquals(true, g.contains(3));
        assertEquals(true, g.contains(2));
        assertEquals(1, g.add(1, 2));
        assertEquals(1, g.add(1, 2));
        assertEquals(2, g.add(2, 3));
        assertEquals(true, g.contains(1, 2));
        assertEquals(false, g.contains(2, 1));
        assertEquals(true, g.contains(2, 3));
        assertEquals(false, g.contains(3, 2));
        assertEquals(false, g.contains(1, 3));
        UndirectedGraph uG = new UndirectedGraph();
        assertEquals(1, uG.add());
        assertEquals(true, uG.contains(1));
        assertEquals(false, uG.contains(2));
        assertEquals(2, uG.add());
        assertEquals(3, uG.add());
        assertEquals(true, uG.contains(3));
        assertEquals(true, uG.contains(2));
        assertEquals(1, uG.add(1, 2));
        assertEquals(1, uG.add(1, 2));
        assertEquals(2, uG.add(2, 3));
        assertEquals(true, uG.contains(1, 2));
        assertEquals(true, uG.contains(2, 1));
        assertEquals(true, uG.contains(2, 3));
        assertEquals(true, uG.contains(3, 2));
        assertEquals(false, uG.contains(1, 3));
    }

    @Test
    public void testVertexSize() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.vertexSize());
        assertEquals(1, dGraph.add());
        assertEquals(2, dGraph.add());
        assertEquals(3, dGraph.add());
        assertEquals(3, dGraph.vertexSize());
        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(0, uDGraph.vertexSize());
        assertEquals(1, uDGraph.add());
        assertEquals(2, uDGraph.add());
        assertEquals(3, uDGraph.add());
        assertEquals(3, uDGraph.vertexSize());
    }

    @Test
    public void testMaxVertex() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.maxVertex());
        assertEquals(1, dGraph.add());
        assertEquals(2, dGraph.add());
        assertEquals(3, dGraph.add());
        assertEquals(3, dGraph.maxVertex());
        assertEquals(4, dGraph.add());
        assertEquals(4, dGraph.maxVertex());
        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(0, uDGraph.maxVertex());
        assertEquals(1, uDGraph.add());
        assertEquals(2, uDGraph.add());
        assertEquals(3, uDGraph.add());
        assertEquals(3, uDGraph.maxVertex());
        assertEquals(4, uDGraph.add());
        assertEquals(4, uDGraph.maxVertex());
    }

    @Test
    public void testEdgeSize() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.edgeSize());
        assertEquals(1, dGraph.add());
        assertEquals(2, dGraph.add());
        assertEquals(3, dGraph.add());
        assertEquals(1, dGraph.add(1, 2));
        assertEquals(2, dGraph.add(2, 3));
        assertEquals(2, dGraph.edgeSize());
        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(0, uDGraph.edgeSize());
        assertEquals(1, uDGraph.add());
        assertEquals(2, uDGraph.add());
        assertEquals(3, uDGraph.add());
        assertEquals(1, uDGraph.add(1, 2));
        assertEquals(2, uDGraph.add(2, 3));
        assertEquals(2, uDGraph.edgeSize());
    }

    @Test
    public void testIsDirected() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(true, dGraph.isDirected());
        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(false, uDGraph.isDirected());
    }

    @Test
    public void testOutDegree() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.outDegree(1));
        dGraph.add();
        dGraph.add();
        dGraph.add();
        assertEquals(0, dGraph.outDegree(2));
        dGraph.add(1, 2);
        dGraph.add(1, 3);
        assertEquals(2, dGraph.outDegree(1));
        assertEquals(0, dGraph.outDegree(3));
        dGraph.add(3, 1);
        assertEquals(1, dGraph.outDegree(3));
    }

    @Test
    public void testInDegree() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.inDegree(1));
        dGraph.add();
        dGraph.add();
        dGraph.add();
        assertEquals(0, dGraph.inDegree(2));
        dGraph.add(1, 2);
        dGraph.add(1, 3);
        assertEquals(0, dGraph.inDegree(1));
        assertEquals(1, dGraph.inDegree(3));
        dGraph.add(3, 1);
        assertEquals(1, dGraph.inDegree(1));
        dGraph.add(2, 1);
        assertEquals(2, dGraph.inDegree(1));
    }

    @Test
    public void testDegree() {
        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(0, uDGraph.degree(1));
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        assertEquals(0, uDGraph.degree(2));
        uDGraph.add(1, 2);
        uDGraph.add(1, 3);
        assertEquals(2, uDGraph.degree(1));
        assertEquals(1, uDGraph.degree(3));
        uDGraph.add(3, 1);
        assertEquals(1, uDGraph.degree(3));
    }

    @Test
    public void testRemove() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.add();
        dGraph.add();
        assertEquals(true, dGraph.contains(1));
        dGraph.remove(1);
        assertEquals(false, dGraph.contains(1));
        dGraph.add();
        assertEquals(true, dGraph.contains(1));
        dGraph.add(1, 2);
        assertEquals(true, dGraph.contains(1, 2));
        dGraph.remove(1);
        assertEquals(false, dGraph.contains(1, 2));
        dGraph.add();
        dGraph.add(1, 2);
        assertEquals(true, dGraph.contains(1, 2));
        dGraph.remove(1, 2);
        assertEquals(false, dGraph.contains(1, 2));
        UndirectedGraph uDGraph = new UndirectedGraph();
        uDGraph.add();
        uDGraph.add();
        assertEquals(true, uDGraph.contains(1));
        uDGraph.remove(1);
        assertEquals(false, uDGraph.contains(1));
        uDGraph.add();
        assertEquals(true, uDGraph.contains(1));
        uDGraph.add(1, 2);
        assertEquals(true, uDGraph.contains(1, 2));
        uDGraph.remove(1);
        assertEquals(false, uDGraph.contains(1, 2));
        uDGraph.add();
        uDGraph.add(1, 2);
        assertEquals(true, uDGraph.contains(1, 2));
        uDGraph.remove(1, 2);
        assertEquals(false, uDGraph.contains(1, 2));
    }

    @Test
    public void testVertices() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        Iteration<Integer> iter = dGraph.vertices();
        int count = 1;
        while (iter.hasNext()) {
            assertEquals(count, iter.next().intValue());
            count += 1;
        }
        UndirectedGraph uDGraph = new UndirectedGraph();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        iter = uDGraph.vertices();
        count = 1;
        while (iter.hasNext()) {
            assertEquals(count, iter.next().intValue());
            count += 1;
        }
    }

    @Test
    public void testSuccessor() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.successor(1, 1));
        assertEquals(0, dGraph.successor(2, 0));
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add(1, 2);
        dGraph.add(1, 3);
        assertEquals(2, dGraph.successor(1, 0));
        assertEquals(3, dGraph.successor(1, 1));
        dGraph.add(2, 3);
        assertEquals(3, dGraph.successor(2, 0));
        dGraph.remove(1);
        assertEquals(0, dGraph.successor(1, 1));
        dGraph.remove(2, 3);
        assertEquals(0, dGraph.successor(2, 0));
    }

    @Test
    public void testPredecessor() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        assertEquals(0, dGraph.predecessor(1, 0));
        assertEquals(0, dGraph.predecessor(2, 1));
        dGraph.add(2, 1);
        dGraph.add(3, 1);
        assertEquals(2, dGraph.predecessor(1, 0));
        assertEquals(3, dGraph.predecessor(1, 1));
        dGraph.add(1, 2);
        assertEquals(1, dGraph.predecessor(2, 0));
    }

    @Test
    public void testNeighbor() {
        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(0, uDGraph.neighbor(1, 1));
        assertEquals(0, uDGraph.neighbor(2, 0));
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add(1, 2);
        uDGraph.add(1, 3);
        assertEquals(2, uDGraph.neighbor(1, 0));
        assertEquals(3, uDGraph.neighbor(1, 1));
        uDGraph.add(2, 3);
        assertEquals(1, uDGraph.neighbor(2, 0));
        assertEquals(3, uDGraph.neighbor(2, 1));
        uDGraph.remove(1);
        assertEquals(0, uDGraph.neighbor(1, 1));
        uDGraph.remove(2, 3);
        assertEquals(0, uDGraph.neighbor(2, 0));
        assertEquals(0, uDGraph.neighbor(2, 1));
    }

    @Test
    public void testSuccessors() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(null, dGraph.successors(1));
        assertEquals(null, dGraph.successors(2));
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add(1, 2);
        dGraph.add(1, 3);
        dGraph.add(2, 4);
        dGraph.add(2, 3);
        Iteration<Integer> iter = dGraph.successors(1);
        assertEquals(2, iter.next().intValue());
        assertEquals(3, iter.next().intValue());
        iter = dGraph.successors(2);
        assertEquals(4, iter.next().intValue());
        assertEquals(3, iter.next().intValue());
        assertEquals(false, iter.hasNext());
    }

    @Test
    public void testPredecessors() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(null, dGraph.predecessors(1));
        assertEquals(null, dGraph.predecessors(2));
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add(3, 2);
        dGraph.add(1, 2);
        dGraph.add(1, 3);
        dGraph.add(2, 3);
        dGraph.add(2, 4);
        Iteration<Integer> iter = dGraph.predecessors(2);
        assertEquals(3, iter.next().intValue());
        assertEquals(1, iter.next().intValue());
        iter = dGraph.predecessors(3);
        assertEquals(1, iter.next().intValue());
        assertEquals(2, iter.next().intValue());
        iter = dGraph.predecessors(4);
        assertEquals(2, iter.next().intValue());
        assertEquals(false, iter.hasNext());
    }

    @Test
    public void testNeighbors() {
        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(null, uDGraph.neighbors(1));
        assertEquals(null, uDGraph.neighbors(2));
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add(1, 2);
        uDGraph.add(1, 3);
        uDGraph.add(2, 4);
        uDGraph.add(2, 3);
        Iteration<Integer> iter = uDGraph.neighbors(1);
        assertEquals(2, iter.next().intValue());
        assertEquals(3, iter.next().intValue());
        iter = uDGraph.successors(2);
        assertEquals(1, iter.next().intValue());
        assertEquals(4, iter.next().intValue());
        assertEquals(3, iter.next().intValue());
        assertEquals(false, iter.hasNext());
    }

    @Test
    public void testEdges() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add(1, 2);
        dGraph.add(1, 3);
        dGraph.add(1, 4);
        dGraph.add(2, 3);
        dGraph.add(2, 4);
        Iteration<int[]> iter = dGraph.edges();
        int[] tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(2, tmp[1]);
        tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(3, tmp[1]);
        tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(4, tmp[1]);
        tmp = iter.next();
        assertEquals(2, tmp[0]);
        assertEquals(3, tmp[1]);
        tmp = iter.next();
        assertEquals(2, tmp[0]);
        assertEquals(4, tmp[1]);
        assertEquals(false, iter.hasNext());
        UndirectedGraph uDGraph = new UndirectedGraph();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add(1, 2);
        uDGraph.add(1, 2);
        uDGraph.add(2, 1);
        uDGraph.add(1, 3);
        uDGraph.add(3, 1);
        iter = uDGraph.edges();
        tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(2, tmp[1]);
        tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(3, tmp[1]);
        assertEquals(false, iter.hasNext());
    }

    @Test
    public void testMine() {
        testContains();
    }

    @Test
    public void testEdgeId() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.edgeId(1, 2));
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add(1, 2);
        dGraph.add(1, 3);
        dGraph.add(3, 2);
        assertEquals(1, dGraph.edgeId(1, 2));
        assertEquals(2, dGraph.edgeId(1, 3));
        assertEquals(3, dGraph.edgeId(3, 2));
        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(0, uDGraph.edgeId(1, 2));
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add(1, 2);
        uDGraph.add(1, 3);
        uDGraph.add(3, 2);
        assertEquals(1, uDGraph.edgeId(1, 2));
        assertEquals(1, uDGraph.edgeId(2, 1));
        assertEquals(2, uDGraph.edgeId(1, 3));
        assertEquals(2, uDGraph.edgeId(3, 1));
        assertEquals(3, uDGraph.edgeId(3, 2));
        assertEquals(3, uDGraph.edgeId(2, 3));
    }

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
        UndirectedGraph uG = new UndirectedGraph();
        assertEquals(0, uG.vertexSize());
        assertEquals(0, uG.edgeSize());
    }

    @Test
    public void testUndirectedMaxVertex() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 3);
        g.add(2, 6);
        g.add(3, 7);
        g.add(3, 8);
        g.add(8, 1);
        g.add(8, 9);
        g.add(8, 10);
        g.add(10, 7);
        assertEquals(12, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(1);
        assertEquals(8, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(10);
        assertEquals(6, g.edgeSize());
        assertEquals(9, g.maxVertex());
    }

    @Test
    public void testUndirectedEdgeSize() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(1, 1);
        g.add(2, 5);
        g.add(2, 3);
        g.add(2, 6);
        g.add(3, 7);
        g.add(3, 8);
        g.add(8, 1);
        g.add(8, 9);
        g.add(8, 10);
        g.add(10, 7);
        assertEquals(13, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(1);
        assertEquals(8, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(2);
        assertEquals(5, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(2, 5);
        assertEquals(5, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(7, 10);
        assertEquals(4, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(8);
        assertEquals(1, g.edgeSize());
        assertEquals(10, g.maxVertex());
    }

    @Test
    public void testDirectedMaxVertex() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 3);
        g.add(2, 6);
        g.add(3, 7);
        g.add(3, 8);
        g.add(8, 1);
        g.add(8, 9);
        g.add(8, 10);
        g.add(10, 7);
        assertEquals(12, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(1);
        assertEquals(8, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(10);
        assertEquals(6, g.edgeSize());
        assertEquals(9, g.maxVertex());
    }

    @Test
    public void testDirectedEdgeSize() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 3);
        g.add(2, 6);
        g.add(3, 7);
        g.add(3, 8);
        g.add(8, 1);
        g.add(8, 9);
        g.add(8, 10);
        g.add(10, 7);
        assertEquals(12, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(1, 2);
        assertEquals(11, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(2, 5);
        assertEquals(10, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(10, 7);
        assertEquals(9, g.edgeSize());
        assertEquals(10, g.maxVertex());
    }
}
