package quantum.demo1;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import quantum.util.Utils;

import java.util.concurrent.TimeUnit;

/**
 * Quantum Performance Effects (demo)
 *
 * demo1 - Exploring ILP bounds
 *
 * @author Sergey Kuksenko
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5, time = 2)
@Measurement(iterations = 5, time = 2)
public class DoubleSumAverageTime {

    public static final int SIZE = 2048;

    private double[] array;

    @Setup
    public void init() {
        array = Utils.newRandomDoubleArray(SIZE);
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public double test1() {
        double sum = 0.0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public double manualUnroll() {
        double sum = 0.0;
        for (int i = 0; i < array.length; i += 4) {
            sum += array[i] + array[i + 1] + array[i + 2] + array[i + 3];
        }
        return sum;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public double test2() {
        double sum0 = 0.0;
        double sum1 = 0.0;
        for (int i = 0; i < array.length; i += 2) {
            sum0 += array[i];
            sum1 += array[i + 1];
        }
        return sum0 + sum1;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public double test4() {
        double sum0 = 0.0;
        double sum1 = 0.0;
        double sum2 = 0.0;
        double sum3 = 0.0;
        for (int i = 0; i < array.length; i += 4) {
            sum0 += array[i];
            sum1 += array[i + 1];
            sum2 += array[i + 2];
            sum3 += array[i + 3];
        }
        return (sum0 + sum1) + (sum2 + sum3);
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public double test8() {
        double sum0 = 0.0;
        double sum1 = 0.0;
        double sum2 = 0.0;
        double sum3 = 0.0;
        double sum4 = 0.0;
        double sum5 = 0.0;
        double sum6 = 0.0;
        double sum7 = 0.0;
        for (int i = 0; i < array.length; i += 8) {
            sum0 += array[i];
            sum1 += array[i + 1];
            sum2 += array[i + 2];
            sum3 += array[i + 3];
            sum4 += array[i + 4];
            sum5 += array[i + 5];
            sum6 += array[i + 6];
            sum7 += array[i + 7];
        }
        return ((sum0 + sum1) + (sum2 + sum3)) + ((sum4 + sum5) + (sum6 + sum7));
    }

}
