package graph;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashMap;

import org.junit.Test;

/** A testing class for ShortestPaths
 * @author Dasheng Chen
 */
public class PathsTesting {
    class VideoGraphPaths extends SimpleShortestPaths {
        public VideoGraphPaths(Graph g, int source, int dest) {
            super(g, source, dest);
            _edgeWeight = new HashMap<SimpleImmutableEntry<Integer,Integer>, Double>();
            _edgeWeight.put(new SimpleImmutableEntry<Integer, Integer>(4, 2), 12.2);
            _edgeWeight.put(new SimpleImmutableEntry<Integer, Integer>(4, 3), 102.0);
            _edgeWeight.put(new SimpleImmutableEntry<Integer, Integer>(4, 5), 11.2);
            _edgeWeight.put(new SimpleImmutableEntry<Integer, Integer>(2, 3), 6.5);
            _edgeWeight.put(new SimpleImmutableEntry<Integer, Integer>(5, 3), 9.1);
            _edgeWeight.put(new SimpleImmutableEntry<Integer, Integer>(5, 6), 30.0);
            _eDis = new HashMap<Integer, Double>();
            _eDis.put(4, 102.0);
            _eDis.put(2, 4.0);
            _eDis.put(5, 5.1);
            _eDis.put(6, 40.0);
        }

        @Override
        protected double getWeight(int u, int v) {
            SimpleImmutableEntry<Integer, Integer> key = new SimpleImmutableEntry<Integer, Integer>(u, v);
            if (_edgeWeight.containsKey(key)) {
                return _edgeWeight.get(key);
            }
            return Double.MAX_VALUE;
        }
        
        @Override
        protected double estimatedDistance(int v) {
            // appropriate estimate
            if (_eDis.containsKey(v)) {
                return _eDis.get(v);
            }
            return 0;
        }
        
        /** Edge weight. */
        private HashMap<SimpleImmutableEntry<Integer, Integer>, Double> _edgeWeight;
        /** Estimated distance. */
        private HashMap<Integer, Double> _eDis;
    }
    
    @Test
    public void testWeights() {
        Graph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(4, 2);
        g.add(4, 3);
        g.add(4, 5);
        g.add(2, 3);
        g.add(5, 3);
        g.add(5, 6);
        VideoGraphPaths vgp = new VideoGraphPaths(g, 4, 3);
        vgp.setPaths();
        
    }
}
