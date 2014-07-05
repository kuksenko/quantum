package quantum.demo3;

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
 * demo3 - Exploring Branch Predictor:
 *   - conditional jumps
 *
 * @author Sergey Kuksenko
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class AbsSum {

    public static final int SIZE = 4 * 1024;

    private static char[] branchPattern;

    static {
        // try your own pattern
        branchPattern = System.getProperty("branch.pattern", "10").toCharArray();
    }

    private int[] regularData;
    private int[] sortedData;
    private int[] shuffledData;

    @Setup
    public void init() {
        regularData = new int[SIZE];
        for (int i = 0, j = 0; i < SIZE; i++) {
            regularData[i] = branchPattern[j] == '1' ? 1 : -1;
            j = (j + 1) % branchPattern.length;
        }
        sortedData = Utils.sortedCopyOf(regularData);
        shuffledData = Utils.shuffledCopyOf(regularData);
    }

    /*
     *
     */
    public static int absSumBranch(int a[]) {
        int sum = 0;
        for (int x : a) {
            if (x < 0) {
                sum -= x;
            } else {
                sum += x;
            }
        }
        return sum;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int branch_regular() {
        return absSumBranch(regularData);
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int branch_sorted() {
        return absSumBranch(sortedData);
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int branch_shuffled() {
        return absSumBranch(shuffledData);
    }

    public static int absSumPredicated(int a[]) {
        int sum = 0;
        for (int x : a) {
            sum += Math.abs(x);
        }
        return sum;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int predicated_regular() {
        return absSumPredicated(regularData);
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int predicated_sorted() {
        return absSumPredicated(sortedData);
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int predicated_shuffled() {
        return absSumPredicated(shuffledData);
    }

}

