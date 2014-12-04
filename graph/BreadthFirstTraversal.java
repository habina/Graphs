package graph;

import java.util.ArrayDeque;

/* See restrictions in Graph.java. */

/** Implements a breadth-first traversal of a graph.  Generally, the
 *  client will extend this class, overriding the visit method as desired
 *  (by default, it does nothing).
 *  @author
 */
public class BreadthFirstTraversal extends Traversal {

    /** A depth-first Traversal of G, using FRINGE as the fringe. */
    protected BreadthFirstTraversal(Graph G) {
        super(G, deque);
        // FIXME
    }

    @Override
    protected boolean visit(int v) {
        return super.visit(v);
    }

    private static ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
}
