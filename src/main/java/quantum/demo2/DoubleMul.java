package quantum.demo2;

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
 * demo2 - Exploring ILP bounds
 *
 * @author Sergey Kuksenko
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5, time = 2)
@Measurement(iterations = 5, time = 2)
public class DoubleMul {

    public static final int SIZE = 2048;

    private double[] array;

    @Setup
    public void init() {
        array = Utils.newRandomDoubleArray(SIZE);
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public double test1() {
        double p = 1.0;
        for (int i = 0; i < array.length; i++) {
            p *= array[i];
        }
        return p;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public double test2() {
        double p0 = 1.0;
        double p1 = 1.0;
        for (int i = 0; i < array.length; i += 2) {
            p0 *= array[i];
            p1 *= array[i + 1];
        }
        return p0 * p1;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public double test4() {
        double p0 = 1.0;
        double p1 = 1.0;
        double p2 = 1.0;
        double p3 = 1.0;
        for (int i = 0; i < array.length; i += 4) {
            p0 *= array[i];
            p1 *= array[i + 1];
            p2 *= array[i + 2];
            p3 *= array[i + 3];
        }
        return (p0 * p1) * (p2 * p3);
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public double test8() {
        double p0 = 1.0;
        double p1 = 1.0;
        double p2 = 1.0;
        double p3 = 1.0;
        double p4 = 1.0;
        double p5 = 1.0;
        double p6 = 1.0;
        double p7 = 1.0;
        for (int i = 0; i < array.length; i += 8) {
            p0 *= array[i];
            p1 *= array[i + 1];
            p2 *= array[i + 2];
            p3 *= array[i + 3];
            p4 *= array[i + 4];
            p5 *= array[i + 5];
            p6 *= array[i + 6];
            p7 *= array[i + 7];
        }
        return ((p0 * p1) * (p2 * p3)) * ((p4 * p5) * (p6 * p7));
    }

}
