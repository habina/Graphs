package graph;

import ucb.junit.textui;

/** Unit tests for the graph package.  This class serves to dispatch
 *  other test classes, which are listed in the argument to runClasses.
 *  @author Dasheng Chen
 */
public class UnitTest {

    /** Run all JUnit tests in the graph package. */
    public static void main(String[] ignored) {
        System.exit(textui.runClasses(graph.GraphTesting.class,
                                      graph.TraversalTesting.class,
                                      graph.PathsTesting.class));
    }

}
