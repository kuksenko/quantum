package quantum.selfstudy1;

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
 * get and try to explain performance results
 *
 * @author Sergey Kuksenko
 */
@State
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class CacheL1 {

    public static final int SIZE = 2 * 1024; // double[SIZE] ~ 16K

    private double[] array;
    private int[] ordered;
    private int[] shuffled;

    @Setup
    public void init() {
        array = Utils.newRandomDoubleArray(SIZE);
        ordered = new int[SIZE];
        for (int i = 0; i < ordered.length; i++) {
            ordered[i] = i;
        }
        shuffled = Utils.shuffledCopyOf(ordered);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public double ordered1() {
        return Sums.sum1(array, ordered);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public double ordered2() {
        return Sums.sum2(array, ordered);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public double ordered4() {
        return Sums.sum4(array, ordered);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public double shuffled1() {
        return Sums.sum1(array, shuffled);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public double shuffled2() {
        return Sums.sum2(array, shuffled);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public double shuffled4() {
        return Sums.sum4(array, shuffled);
    }

}
