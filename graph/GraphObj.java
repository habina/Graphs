package graph;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Dasheng Chen
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        _pqVertex = new PriorityQueue<Integer>();
        _pqEdgeId = new PriorityQueue<Integer>();
        _pqVertex.add(1);
        _pqEdgeId.add(1);
        _nodeMap = new HashMap<Integer, GraphNode>();
        _edgesList = new ArrayList<int[]>();
        _edgesID = new
            HashMap<SimpleImmutableEntry<Integer, Integer>, Integer>();
    }

    @Override
    public int vertexSize() {
        return _nodeMap.size();
    }

    @Override
    public int maxVertex() {
        return _maxVertex;
    }

    @Override
    public int edgeSize() {
        return _edgesList.size();
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
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
        return _nodeMap.containsKey(u);
    }

    @Override
    public boolean contains(int u, int v) {
        if (_nodeMap.containsKey(u) && _nodeMap.containsKey(v)) {
            GraphNode gn = _nodeMap.get(u);
            if (isDirected()) {
                return gn.successor.contains(v);
            } else {
                return gn.successor.contains(v) || gn.predecessor.contains(v);
            }
        }
        return false;
    }

    @Override
    public int add() {
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
        if (contains(u) && contains(v)) {
            if (contains(u, v)) {
                return u;
            }
            int avalId = _pqEdgeId.poll();
            GraphNode uNode = _nodeMap.get(u);
            GraphNode vNode = _nodeMap.get(v);
            uNode.successor.add(v);
            vNode.predecessor.add(u);
            if (isDirected()) {
                _edgesList.add(new int[]{u, v});
                _edgesID.put(new SimpleImmutableEntry<Integer, Integer>(u, v),
                    avalId);
            } else {
                int larger = Math.max(u, v);
                int smaller = Math.min(u, v);
                _edgesList.add(new int[]{smaller, larger});
                _edgesID.put(new SimpleImmutableEntry<Integer, Integer>(
                    smaller, larger), avalId);
                uNode.predecessor.add(v);
                vNode.successor.add(u);
            }
            if (_pqEdgeId.isEmpty()) {
                _pqEdgeId.offer(_edgesList.size() + 1);
            }
        }
        return u;
    }

    /** find edge index number.
     * @param u vertex nummber
     * @param v vertex number
     * @return index of this edge
     */
    private int indexEdges(int u, int v) {
        int count = -1;
        boolean found = false;
        for (int[] tuple : _edgesList) {
            count += 1;
            if (tuple[0] == u && tuple[1] == v) {
                found = true;
                break;
            }
        }
        if (!isDirected() && !found) {
            count = -1;
            found = false;
            for (int[] tuple : _edgesList) {
                count += 1;
                if (tuple[0] == v && tuple[1] == u) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            return count;
        } else {
            return -1;
        }
    }

    @Override
    public void remove(int v) {
        if (contains(v)) {
            GraphNode gn = _nodeMap.get(v);
            for (Integer preVertex : gn.predecessor) {
                ArrayDeque<Integer> suc = _nodeMap.get(preVertex).successor;
                SimpleImmutableEntry<Integer, Integer> key =
                    new SimpleImmutableEntry<Integer, Integer>(preVertex, v);
                if (_edgesID.containsKey(key)) {
                    int oldId = _edgesID.get(key);
                    _pqEdgeId.add(oldId);
                    _edgesID.remove(key);
                }
                suc.remove(v);
                int edIndex = indexEdges(preVertex, v);
                if (edIndex != -1) {
                    _edgesList.remove(edIndex);
                }
            }
            for (Integer sucVertex : gn.successor) {
                ArrayDeque<Integer> prd = _nodeMap.get(sucVertex).predecessor;
                SimpleImmutableEntry<Integer, Integer> key =
                    new SimpleImmutableEntry<Integer, Integer>(v, sucVertex);
                if (_edgesID.containsKey(key)) {
                    int oldId = _edgesID.get(key);
                    _pqEdgeId.add(oldId);
                    _edgesID.remove(key);
                }
                prd.remove(v);
                if (isDirected()) {
                    int edIndex = indexEdges(v, sucVertex);
                    if (edIndex != -1) {
                        _edgesList.remove(edIndex);
                    }
                }
            }
            _nodeMap.remove(v);
            if (v >= _maxVertex) {
                TreeSet<Integer> ts = new TreeSet<Integer>(_nodeMap.keySet());
                _maxVertex = ts.last();
            }
            _pqVertex.add(v);
        }
    }

    @Override
    public void remove(int u, int v) {
        GraphNode uNode = _nodeMap.get(u);
        GraphNode vNode = _nodeMap.get(v);
        if (uNode != null && vNode != null && contains(u, v)) {
            uNode.successor.remove(v);
            vNode.predecessor.remove(u);
            if (isDirected()) {
                int edIndex = indexEdges(u, v);
                if (edIndex != -1) {
                    _edgesList.remove(edIndex);
                }
            } else {
                uNode.predecessor.remove(v);
                vNode.successor.remove(u);
                int larger = Math.max(u, v);
                int smaller = Math.min(u, v);
                SimpleImmutableEntry<Integer, Integer> key =
                    new SimpleImmutableEntry<Integer, Integer>(smaller, larger);
                int oldId = _edgesID.get(key);
                _edgesID.remove(key);
                _pqEdgeId.add(oldId);
                int edIndex = indexEdges(smaller, larger);
                if (edIndex != -1) {
                    _edgesList.remove(edIndex);
                }
            }
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        return Iteration.iteration(_nodeMap.keySet().iterator());
    }

    @Override
    public int successor(int v, int k) {
        if (contains(v)) {
            GraphNode gn = _nodeMap.get(v);
            if (k >= gn.successor.size() || k < 0) {
                return 0;
            } else {
                int count = 0;
                for (Integer i : gn.successor) {
                    if (count == k) {
                        return i;
                    }
                    count += 1;
                }
            }
        }
        return 0;
    }

    @Override
    public abstract int predecessor(int v, int k);

    @Override
    public Iteration<Integer> successors(int v) {
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
        return Iteration.iteration(_edgesList.iterator());
    }

    @Override
    protected boolean mine(int v) {
        return contains(v);
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!this.contains(v)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        SimpleImmutableEntry<Integer, Integer> key = null;
        if (isDirected()) {
            key = new SimpleImmutableEntry<Integer, Integer>(u, v);
        } else {
            int larger = Math.max(u, v);
            int smaller = Math.min(u, v);
            key = new SimpleImmutableEntry<Integer, Integer>(smaller, larger);
        }
        if (_edgesID.containsKey(key)) {
            return _edgesID.get(key).intValue();
        } else {
            return 0;
        }
    }

    /** A graph node class. */
    protected class GraphNode {
        /** value.*/
        protected int value;
        /** predecessor list. */
        protected ArrayDeque<Integer> predecessor = new ArrayDeque<Integer>();
        /** successor list. */
        protected ArrayDeque<Integer> successor = new ArrayDeque<Integer>();
    }

    /** A PQ, head is the smallest available vertex number. */
    private PriorityQueue<Integer> _pqVertex;
    /** A PQ, head is the smallest available edge id number. */
    private PriorityQueue<Integer> _pqEdgeId;
    /** A hashMap that mapping from integer to corresponding vertex. */
    protected HashMap<Integer, GraphNode> _nodeMap;
    /** Max vertex. */
    private int _maxVertex;
    /** Edges list. */
    private ArrayList<int[]> _edgesList;
    /** Edges ID. */
    private HashMap<SimpleImmutableEntry<Integer, Integer>, Integer> _edgesID;
}
