package make;

import graph.LabeledGraph;

/** A directed, labeled subtype of Graph that describes dependencies between
 *  targets in a Makefile.
 *  @author Dasheng Chen
 */
class Depends extends LabeledGraph<Rule, Void> {
    // REPLACE WITH SOLUTION
    /** An empty dependency graph. */
    Depends() {
        super(new graph.DirectedGraph());
//        super(null);
    }
    
}
