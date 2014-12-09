package graph;

/* See restrictions in Graph.java. */

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author Dasheng Chen
 */
public abstract class ShortestPaths {
    
    class AStarTraversal extends Traversal {

        protected AStarTraversal(Graph G, Queue<Integer> fringe) {
            super(G, fringe);
        }
        
        @Override
        protected boolean visit(int v) {
            if (v == ShortestPaths.this.getDest()) {
                return false;
            } else {
              for (Integer w : _G.successors(v)) {
                  double n = getWeight(v, w);
                  double distW = getWeight(w);
                  double distV = getWeight(v);
                  double newDist = n + distV;
                  if (distW > newDist) {
                      setWeight(w, newDist);
                      setPredecessor(w, v);
                  }
              }
              return true;
            }
        }
    }
    
    class AStarQueue extends AbstractQueue<Integer> {
        
        class Vertex{
            public Vertex(int vertex, double value) {
                _vertex = vertex;
                _value = value;
            }
            
            public int get_vertex() {
                return _vertex;
            }

            public void set_vertex(int _vertex) {
                this._vertex = _vertex;
            }

            public double get_value() {
                return _value;
            }

            public void set_value(double _value) {
                this._value = _value;
            }

            /** vertex number. */
            private int _vertex;
            /** weight plus estimated distance. */
            private double _value;
        }
        
        class VertexComparator implements Comparator<Vertex>{
            @Override
            public int compare(Vertex o1, Vertex o2) {
                if (o1.get_value() == o2.get_value()) {
                    return 0;
                } else if (o1.get_value() < o2.get_value()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        
        public AStarQueue() {
            _pq = new PriorityQueue<Vertex>(1, new VertexComparator());
            _vertexNumber = new ArrayList<Integer>();
        }
        
        @Override
        public boolean offer(Integer e) {
            _vertexNumber.add(e);
            return _pq.offer(new Vertex(e, getWeight(e) + estimatedDistance(e)));
        }

        @Override
        public Integer poll() {
            Vertex v = _pq.poll();
            if (v == null) {
                return null;
            } else {
                _vertexNumber.remove(new Integer(v.get_vertex()));
                return v.get_vertex();
            }
        }

        @Override
        public Integer peek() {
            Vertex v = _pq.peek();
            if (v == null) {
                return null;
            } else {
                return v.get_vertex();
            }
        }

        @Override
        public Iterator<Integer> iterator() {
            return _vertexNumber.iterator();
        }

        @Override
        public int size() {
            return _pq.size();
        }
        
        /** A PQ for store vertex.*/
        private PriorityQueue<Vertex> _pq;
        /** ArrayList of vertex number. */
        private ArrayList<Integer> _vertexNumber;
    }

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        // FIXME
    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        // FIXME
        _path = new LinkedList<Integer>();
        _AStarTraversal = new AStarTraversal(_G, new AStarQueue());
        for (Integer i : _G.vertices()) {
            setWeight(i, Double.MAX_VALUE);
            setPredecessor(i, -1);
        }
        setWeight(getSource(), 0);
        _AStarTraversal.traverse(getSource());
    }

    /** Returns the starting vertex. */
    public int getSource() {
        // FIXME
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        // FIXME
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        // FIXME
        while (v != getSource()) {
            _path.addFirst(v);
            v = getPredecessor(v);
        }
        _path.addFirst(getSource());
        return _path;
    }

    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    // FIXME

    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
    // FIXME
    /** A Star traversal. */
    private AStarTraversal _AStarTraversal;
    /** A path. */
    private LinkedList<Integer> _path;
}
