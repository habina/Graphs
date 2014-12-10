package graph;

import java.util.HashMap;

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author Dasheng Chen
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        _predecessor = new HashMap<Integer, Integer>();
        _weight = new HashMap<Integer, Double>();
    }

    @Override
    public double getWeight(int v) {
        if (_G.contains(v)) {
            return _weight.get(v);
        } else {
            return Double.MAX_VALUE;
        }
    }

    @Override
    protected void setWeight(int v, double w) {
        _weight.put(v, w);
    }

    @Override
    public int getPredecessor(int v) {
        if (_predecessor.containsKey(v)) {
            return _predecessor.get(v);
        } else {
            return 0;
        }
    }

    @Override
    protected void setPredecessor(int v, int u) {
        _predecessor.put(v, u);
    }

    /** Predecessor mapping. */
    protected HashMap<Integer, Integer> _predecessor;
    /** Edge weight. */
    protected HashMap<Integer, Double> _weight;
}
