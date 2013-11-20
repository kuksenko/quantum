package quantum.selfstudy0;

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
public class BadValue {

    public static final int SIZE = 1024;

    public double[] src;
    public double[] dst;

    public double VALUE1 = 0.01;
    public double VALUE2 = Double.MIN_NORMAL - Double.MIN_VALUE;

    @Setup
    public void init() {
        src = Utils.newRandomDoubleArray(SIZE);
        dst = new double[SIZE];
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public void testAddNormalValue() {
        for (int i = 0; i < src.length; i++) {
            dst[i] = src[i] + VALUE1;
        }
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public void testAddBadValue() {
        for (int i = 0; i < src.length; i++) {
            dst[i] = src[i] + VALUE2;
        }
    }

}
