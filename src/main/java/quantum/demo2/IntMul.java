package quantum.demo2;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import quantum.util.Utils;

import java.util.concurrent.TimeUnit;

/**
 * Quantum Performance Effects (demo)
 *
 * demo2 - Exploring ILP bounds
 *
 * @author Sergey Kuksenko
 */
@State
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class IntMul {

    public static final int SIZE = 2048;

    private int[] array;

    @Setup
    public void init() {
        array = Utils.newRandomIntArray(SIZE);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int test1() {
        int p = 1;
        for (int i = 0; i < array.length; i++) {
            p *= array[i];
        }
        return p;
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int test2() {
        int p0 = 1;
        int p1 = 1;
        for (int i = 0; i < array.length; i += 2) {
            p0 *= array[i];
            p1 *= array[i + 1];
        }
        return p0 * p1;
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int test4() {
        int p0 = 1;
        int p1 = 1;
        int p2 = 1;
        int p3 = 1;
        for (int i = 0; i < array.length; i += 4) {
            p0 *= array[i];
            p1 *= array[i + 1];
            p2 *= array[i + 2];
            p3 *= array[i + 3];
        }
        return (p0 * p1) * (p2 * p3);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int test4z() {
        int p0 = 1;
        int p1 = 1;
        int p2 = 0;// Here, we did misprint intentionally. Run it! :)
        int p3 = 1;
        for (int i = 0; i < array.length; i += 4) {
            p0 *= array[i];
            p1 *= array[i + 1];
            p2 *= array[i + 2];
            p3 *= array[i + 3];
        }
        return (p0 * p1) * (p2 * p3);
    }

}
