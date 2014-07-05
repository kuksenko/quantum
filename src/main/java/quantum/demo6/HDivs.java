package quantum.demo6;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

/**
 * Quantum Performance Effects (demo)
 *
 * demo6 - How does SMT share Functional Units?
 *
 * hint: microbenchmarks should be affinited to different pair of logical CPUs.
 *
 * @author Sergey Kuksenko
 */
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class HDivs {


    public static int i0 = 1173;
    public static int i1 = 233;
    public static int i2 = 67;

    public static double d0 = 1173.1;
    public static double d1 = 233.2;
    public static double d2 = 67.3;

    @Benchmark
    public int lightIntDiv() {
        return i0 / i1;
    }

    @Benchmark
    @OperationsPerInvocation(5)
    public int heavyIntDiv() {
        return (i0 / i2) / ((i0 / i1) / (i1 / i2));
    }

    @Benchmark
    public double lightDoubleDiv() {
        return d0 / d1;
    }

    @Benchmark
    @OperationsPerInvocation(5)
    public double heavyDoubleDiv() {
        return (d0 / d2) / ((d1 / d2) / (d0 / d1));
    }

    @Benchmark
    @Group("lightII")
    public int int0() {
        return lightIntDiv();
    }

    @Benchmark
    @Group("lightII")
    public int int1() {
        return lightIntDiv();
    }

    @Benchmark
    @OperationsPerInvocation(5)
    @Group("heavyII")
    public int intHeavy0() {
        return heavyIntDiv();
    }

    @Benchmark
    @OperationsPerInvocation(5)
    @Group("heavyII")
    public int intHeavy1() {
        return heavyIntDiv();
    }

    @Benchmark
    @Group("lightDD")
    public double double0() {
        return lightDoubleDiv();
    }

    @Benchmark
    @Group("lightDD")
    public double double1() {
        return lightDoubleDiv();
    }

    @Benchmark
    @OperationsPerInvocation(5)
    @Group("heavyDD")
    public double doubleHeavy0() {
        return heavyDoubleDiv();
    }

    @Benchmark
    @OperationsPerInvocation(5)
    @Group("heavyDD")
    public double doubleHeavy1() {
        return heavyDoubleDiv();
    }

    @Benchmark
    @Group("lightID")
    public int intMixLight() {
        return lightIntDiv();
    }

    @Benchmark
    @Group("lightID")
    public double doubleMixLight() {
        return lightDoubleDiv();
    }

    @Benchmark
    @OperationsPerInvocation(5)
    @Group("heavyID")
    public int intMixHeavy() {
        return heavyIntDiv();
    }

    @Benchmark
    @OperationsPerInvocation(5)
    @Group("heavyID")
    public double doubleMixHeavy() {
        return heavyDoubleDiv();
    }

}
