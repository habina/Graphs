package graph;

import static org.junit.Assert.*;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

/** A testing class for ShortestPaths
 * @author Dasheng Chen
 */
public class PathsTesting {
    class VideoGraphPaths extends SimpleShortestPaths {
        public VideoGraphPaths(Graph g, int source, int dest) {
            super(g, source, dest);
            _eCost = new
                HashMap<SimpleImmutableEntry<Integer, Integer>, Double>();
            _eDis = new HashMap<Integer, Double>();
            setCost(new SimpleImmutableEntry<Integer, Integer>(4, 2), 12.2);
            setCost(new SimpleImmutableEntry<Integer, Integer>(4, 3), 102.0);
            setCost(new SimpleImmutableEntry<Integer, Integer>(4, 5), 11.2);
            setCost(new SimpleImmutableEntry<Integer, Integer>(2, 3), 6.5);
            setCost(new SimpleImmutableEntry<Integer, Integer>(5, 3), 9.1);
            setCost(new SimpleImmutableEntry<Integer, Integer>(5, 6), 30.0);
            setCost(new SimpleImmutableEntry<Integer, Integer>(4, 7), 1.0);
            setCost(new SimpleImmutableEntry<Integer, Integer>(7, 1), 1.0);
            setDistance(4, 102.0);
            setDistance(2, 4.0);
            setDistance(5, 5.1);
            setDistance(6, 40.0);
            setDistance(1, 300.0);
            setDistance(7, 400.0);
        }

        private void setCost(SimpleImmutableEntry<Integer, Integer> sie,
            double cost) {
            _eCost.put(sie, cost);
        }

        private void setDistance(int v, double d) {
            _eDis.put(v, d);
        }

        @Override
        protected double getWeight(int u, int v) {
            if (u == 7 && v == 1) {
                throw new IllegalArgumentException();
            }
            SimpleImmutableEntry<Integer, Integer> key =
                new SimpleImmutableEntry<Integer, Integer>(u, v);
            if (_eCost.containsKey(key)) {
                return _eCost.get(key);
            }
            return Double.MAX_VALUE;
        }

        @Override
        protected double estimatedDistance(int v) {
            if (v == 1) {
                throw new IllegalArgumentException();
            }
            if (_eDis.containsKey(v)) {
                return _eDis.get(v);
            }
            return 0;
        }

        /** Edge cost. */
        private HashMap<SimpleImmutableEntry<Integer, Integer>, Double> _eCost;
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
        g.add();
        g.add(4, 2);
        g.add(4, 3);
        g.add(4, 5);
        g.add(2, 3);
        g.add(5, 3);
        g.add(5, 6);
        g.add(4, 7);
        g.add(7, 1);
        VideoGraphPaths vgp = new VideoGraphPaths(g, 4, 3);
        vgp.setPaths();
        List<Integer> path = vgp.pathTo();
        Iterator<Integer> iter = path.iterator();
        assertEquals(4, iter.next().intValue());
        assertEquals(2, iter.next().intValue());
        assertEquals(3, iter.next().intValue());
    }
}
