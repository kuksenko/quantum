package quantum.demo6;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

/**
 * Quantum Performance Effects (demo)
 *
 * demo6 - How does SMT share Functional Units?
 *
 * hint: microbenchmarks should be affinited to different pairs of logical CPUs.
 *
 * @author Sergey Kuksenko
 */
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class HDivs {


    public static int i0 = 1173;
    public static int i1 = 233;
    public static int i2 = 67;

    public static double d0 = 1173.1;
    public static double d1 = 233.2;
    public static double d2 = 67.3;

    @Benchmark
    @OperationsPerInvocation(5)
    public int intDiv() {
        return (i0 / i2) / ((i0 / i1) / (i1 / i2));
    }

    @Benchmark
    @OperationsPerInvocation(5)
    public double doubleDiv() {
        return (d0 / d2) / ((d1 / d2) / (d0 / d1));
    }

    @Benchmark
    @OperationsPerInvocation(5)
    @Group("int_int")
    public int iiDiv0() {
        return intDiv();
    }

    @Benchmark
    @OperationsPerInvocation(5)
    @Group("int_int")
    public int iiDiv1() {
        return intDiv();
    }

    @Benchmark
    @OperationsPerInvocation(5)
    @Group("double_double")
    public double ddDiv0() {
        return doubleDiv();
    }

    @Benchmark
    @OperationsPerInvocation(5)
    @Group("double_double")
    public double ddDiv1() {
        return doubleDiv();
    }

    @Benchmark
    @OperationsPerInvocation(5)
    @Group("double_int")
    public int diDivInt() {
        return intDiv();
    }

    @Benchmark
    @OperationsPerInvocation(5)
    @Group("double_int")
    public double diDivDouble() {
        return doubleDiv();
    }

}
