package graph;

/* See restrictions in Graph.java. */

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Dasheng Chen
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        // FIXME
        if (this.contains(v)) {
            return this._nodeMap.get(v).predecessor.size();
        }
        return 0;
    }

    @Override
    public int predecessor(int v, int k) {
        // FIXME
        if (contains(v)) {
            GraphNode gn = _nodeMap.get(v);
            if (k >= gn.predecessor.size() || k < 0) {
                return 0;
            } else {
                return gn.predecessor.get(k);
            }
        }
        return 0;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        // FIXME
        if (contains(v)) {
            return Iteration.iteration(this._nodeMap.get(v).predecessor.iterator());
        }
        return null;
    }

    // FIXME

}
