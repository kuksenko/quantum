package quantum.demo6;

import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

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
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MapGet {

    public static final int SIZE = 1024*2;
    public static final int FROM = 20001; // shift key values from zero to avoid boxed values caching

    private int[] keys;
    private Integer[] boxedKeys;

    public TIntIntMap third_party_map;
    public Map<Integer, Integer> jdk_map;

    @Setup(Level.Trial)
    public void setup() {
        third_party_map = new TIntIntHashMap();
        jdk_map = new HashMap<>();
        keys = IntStream.range(FROM, FROM + SIZE).toArray();
        boxedKeys = IntStream.range(FROM, FROM + SIZE).boxed().toArray(Integer[]::new);
        for (int key : keys) {
            third_party_map.put(key, key / 2);
        }
        for (Integer key : boxedKeys) {
            jdk_map.put(key, key / 2);
        }
    }

    @Benchmark
    public int test3dParty() {
        int s = 0;
        for (int key : keys) {
            s += third_party_map.get(key);
        }
        return s;
    }

    @Benchmark
    public int testJdkPrimitive() {
        int s = 0;
        for (int key : keys) {
            s += jdk_map.get(key);
        }
        return s;
    }

    @Benchmark
    public int testJdkBoxed() {
        int s = 0;
        for (Integer key : boxedKeys) {
            s += jdk_map.get(key);
        }
        return s;
    }

}
