package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/* See restrictions in Graph.java. */

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Dasheng Chen
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        // FIXME
        _pqVertex = new PriorityQueue<Integer>();
        _pqVertex.add(1);
        _nodeMap = new HashMap<Integer, GraphNode>();
        _edgesList = new ArrayList<int[]>();
    }

    @Override
    public int vertexSize() {
        // FIXME
        return _nodeMap.size();
    }

    @Override
    public int maxVertex() {
        // FIXME
        return _maxVertex;
    }

    @Override
    public int edgeSize() {
        // FIXME
        if (isDirected()) {
            return _edgesList.size();
        }
        return _edgesList.size() / 2;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        // FIXME
        GraphNode gn = _nodeMap.get(v);
        if (gn == null) {
            return 0;
        }
        return gn.successor.size();
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        // FIXME
        return _nodeMap.containsKey(u);
    }

    @Override
    public boolean contains(int u, int v) {
        // FIXME
        if (_nodeMap.containsKey(u) && _nodeMap.containsKey(v)) {
            GraphNode gn = _nodeMap.get(u);
            return gn.successor.contains(v);
        }
        return false;
    }

    @Override
    public int add() {
        // FIXME
        int vertex = _pqVertex.poll();
        if (vertex > _maxVertex) {
            _maxVertex = vertex;
        }
        GraphNode gn = new GraphNode();
        gn.value = vertex;
        _nodeMap.put(vertex, gn);
        if (_pqVertex.isEmpty()) {
            _pqVertex.add(_nodeMap.size() + 1);
        }
        return vertex;
    }

    @Override
    public int add(int u, int v) {
        // FIXME
        if (contains(u) && contains(v)) {
            if (containsEdges(u, v)) {
                return u;
            }
            GraphNode uNode = _nodeMap.get(u);
            GraphNode vNode = _nodeMap.get(v);
            uNode.successor.add(v);
            vNode.predecessor.add(u);
            _edgesList.add(new int[]{u, v});
            if (!isDirected()) {
                uNode.predecessor.add(v);
                vNode.successor.add(u);
                _edgesList.add(new int[]{v, u});
            }
        }
        return u;
    }
    
    private boolean containsEdges(int u, int v) {
        for (int[] tuple : _edgesList) {
            if (tuple[0] == u && tuple[1] == v) {
                return true;
            }
        }
        return false;
    }

    private int indexEdges(int u, int v) {
        int count = -1;
        for (int[] tuple : _edgesList) {
            count += 1;
            if (tuple[0] == u && tuple[1] == v) {
                break;
            }
        }
        return count;
    }

    @Override
    public void remove(int v) {
        // FIXME
        if (contains(v)) {
            GraphNode gn = _nodeMap.get(v);
            for (Integer preVertex : gn.predecessor) {
                ArrayList<Integer> suc = _nodeMap.get(preVertex).successor;
                suc.remove(suc.indexOf(v));
                _edgesList.remove(indexEdges(preVertex, v));
            }
            for (Integer sucVertex : gn.successor) {
                ArrayList<Integer> prd = _nodeMap.get(sucVertex).predecessor;
                prd.remove(prd.indexOf(v));
                _edgesList.remove(indexEdges(v, sucVertex));
            }
            _nodeMap.remove(v);
            _pqVertex.add(v);
        }
    }

    @Override
    public void remove(int u, int v) {
        // FIXME
        GraphNode uNode = _nodeMap.get(u);
        GraphNode vNode = _nodeMap.get(v);
        if (uNode != null && vNode != null && containsEdges(u, v)) {
            uNode.successor.remove(uNode.successor.indexOf(v));
            vNode.predecessor.remove(vNode.predecessor.indexOf(u));
            _edgesList.remove(indexEdges(u, v));
            if (!isDirected()) {
                uNode.predecessor.remove(uNode.predecessor.indexOf(v));
                vNode.successor.remove(vNode.successor.indexOf(u));
                _edgesList.remove(indexEdges(v, u));
            }
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        // FIXME
        return Iteration.iteration(_nodeMap.keySet().iterator());
    }

    @Override
    public int successor(int v, int k) {
        // FIXME
        if (contains(v)) {
            GraphNode gn = _nodeMap.get(v);
            if (k >= gn.successor.size() || k < 0) {
                return 0;
            } else {
                return gn.successor.get(k);
            }
        }
        return 0;
    }

    @Override
    public abstract int predecessor(int v, int k);

    @Override
    public Iteration<Integer> successors(int v) {
        // FIXME
        if (contains(v)) {
            GraphNode gn = _nodeMap.get(v);
            return Iteration.iteration(gn.successor.iterator());
        }
        return null;
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        // FIXME
        return Iteration.iteration(_edgesList.iterator());
    }

    @Override
    protected boolean mine(int v) {
        // FIXME
        return contains(v);
    }

    @Override
    protected void checkMyVertex(int v) {
        // FIXME
        if (!this.contains(v)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        // FIXME
        int id = 0;
        for (int[] tuple : _edgesList) {
            id += 1;
            if (tuple[0] == u && tuple[1] == v) {
                break;
            }
        }
        return id;
    }

    // FIXME
    /** A graph node class. */
    class GraphNode {
        int value;
        ArrayList<Integer> predecessor = new ArrayList<Integer>();
        ArrayList<Integer> successor = new ArrayList<Integer>();
    }

    /** A PQ, head is the smallest available vertex number. */
    private PriorityQueue<Integer> _pqVertex;

    /** A hashMap that mapping from integer to corresponding vertex. */
    HashMap<Integer, GraphNode> _nodeMap;
    
    /** Max vertex. */
    private int _maxVertex;
    
    /** Edges list. */
    private ArrayList<int[]> _edgesList;
}
