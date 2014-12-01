package graph;

/* See restrictions in Graph.java. */

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        // FIXME
    }

    @Override
    public int vertexSize() {
        // FIXME
        return 0;
    }

    @Override
    public int maxVertex() {
        // FIXME
        return 0;
    }

    @Override
    public int edgeSize() {
        // FIXME
        return 0;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        // FIXME
        return 0;
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        // FIXME
        return false;
    }

    @Override
    public boolean contains(int u, int v) {
        // FIXME
        return false;
    }

    @Override
    public int add() {
        // FIXME
        return 0;
    }

    @Override
    public int add(int u, int v) {
        // FIXME
        return 0;
    }

    @Override
    public void remove(int v) {
        // FIXME
    }

    @Override
    public void remove(int u, int v) {
        // FIXME
    }

    @Override
    public Iteration<Integer> vertices() {
        // FIXME
        return null;
    }

    @Override
    public int successor(int v, int k) {
        // FIXME
        return 0;
    }

    @Override
    public abstract int predecessor(int v, int k);

    @Override
    public Iteration<Integer> successors(int v) {
        // FIXME
        return null;
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        // FIXME
        return null;
    }

    @Override
    protected boolean mine(int v) {
        // FIXME
        return false;
    }

    @Override
    protected void checkMyVertex(int v) {
        // FIXME
    }

    @Override
    protected int edgeId(int u, int v) {
        // FIXME
        return 0;
    }

    // FIXME

}
