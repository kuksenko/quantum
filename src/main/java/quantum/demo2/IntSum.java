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
public class IntSum {

    public static final int SIZE = 2048;

    private int[] array;

    @Setup
    public void init() {
        array = Utils.newRandomIntArray(SIZE);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int test1() {
        int s = 0;
        for (int i = 0; i < array.length; i++) {
            s += array[i];
        }
        return s;
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int test2() {
        int s0 = 0;
        int s1 = 0;
        for (int i = 0; i < array.length; i += 2) {
            s0 += array[i];
            s1 += array[i + 1];
        }
        return s0 + s1;
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int test4() {
        int s0 = 0;
        int s1 = 0;
        int s2 = 0;
        int s3 = 0;
        for (int i = 0; i < array.length; i += 4) {
            s0 += array[i];
            s1 += array[i + 1];
            s2 += array[i + 2];
            s3 += array[i + 3];
        }
        return (s0 + s1) + (s2 + s3);
    }

}
