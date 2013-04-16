package quantum.demo6;

import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import quantum.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Quantum Performance Effects (demo)
 *
 * demo6 - How does SMT share Functional Units?
 *
 * hint:
 *  - microbenchmark from the class should be affinited to one logical CPU
 *  - quantum.demo6.Hyper.troll() microbenchmark should be affinited to other logical CPU
 *  - look over different logical CPUs pairs and explain results.
 *
 * @author Sergey Kuksenko
 */
@State(Scope.Benchmark)
public class MapGet {

    public static final int SIZE = 1024;

    private int[] keys;
    private Integer[] boxedKeys;

    public TIntIntMap tmap;
    public Map<Integer, Integer> jmap;

    @Setup(Level.Trial)
    public void init0() {
        tmap = new TIntIntHashMap();
        jmap = new HashMap<>();

        keys = Utils.newRandomIntArray(SIZE);
        boxedKeys = new Integer[SIZE];
        for (int i = 0; i < SIZE; i++) {
            boxedKeys[i] = keys[i];
        }

        for (int key : keys) {
            tmap.put(key, key / 2);
            jmap.put(key, key / 2);
        }
    }

    @GenerateMicroBenchmark
    public int testGetT() {
        int s = 0;
        for (int key : keys) {
            s += tmap.get(key);
        }
        return s;
    }

    @GenerateMicroBenchmark
    public int testGetJ() {
        int s = 0;
        for (int key : keys) {
            s += jmap.get(key);
        }
        return s;
    }

    @GenerateMicroBenchmark
    public int testGetJBoxed() {
        int s = 0;
        for (Integer key : boxedKeys) {
            s += jmap.get(key);
        }
        return s;
    }

}
