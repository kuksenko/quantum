package quantum.selfstudy4;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
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
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class LoopBack {

    public static final int K = 1024;
    public static final int M = K * K;

    private int[] arr1K;
    private int[] arr32K;
    private int[] arr4M;

    @Setup
    public void initI() {
        arr1K = Utils.newRandomIntArray(K);
        arr32K = Utils.newRandomIntArray(32 * K);
        arr4M = Utils.newRandomIntArray(4 * M);
    }

    public static int foreachSum(int[] arr) {
        int s = 0;
        for (int x : arr) {
            s += x;
        }
        return s;
    }

    public static int forwardSum(int[] arr) {
        int s = 0;
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
        }
        return s;
    }

    public static int backwardSum(int[] arr) {
        int s = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            s += arr[i];
        }
        return s;
    }

    @Benchmark
    @OperationsPerInvocation(K)
    public int foreach1K() {
        return foreachSum(arr1K);
    }

    @Benchmark
    @OperationsPerInvocation(K)
    public int forward1K() {
        return forwardSum(arr1K);
    }

    @Benchmark
    @OperationsPerInvocation(K)
    public int backward1K() {
        return backwardSum(arr1K);
    }

    @Benchmark
    @OperationsPerInvocation(32 * K)
    public int foreach32K() {
        return foreachSum(arr32K);
    }

    @Benchmark
    @OperationsPerInvocation(32 * K)
    public int forward32K() {
        return forwardSum(arr32K);
    }

    @Benchmark
    @OperationsPerInvocation(32 * K)
    public int backward32K() {
        return backwardSum(arr32K);
    }

    @Benchmark
    @OperationsPerInvocation(4 * M)
    public int foreach4M() {
        return foreachSum(arr4M);
    }

    @Benchmark
    @OperationsPerInvocation(4 * M)
    public int forward4M() {
        return forwardSum(arr4M);
    }

    @Benchmark
    @OperationsPerInvocation(4 * M)
    public int backward4M() {
        return backwardSum(arr4M);
    }

}
