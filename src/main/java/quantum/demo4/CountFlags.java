package quantum.demo4;

import org.openjdk.jmh.annotations.BenchmarkType;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import quantum.util.Utils;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Quantum Performance Effects (demo)
 *
 * demo4 - Exploring Branch Predictor:
 *   - conditional jumps
 *
 * @author Sergey Kuksenko
 */
@State
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class CountFlags {

    public static final int SIZE = 4 * 1024;

    private boolean[] shuffledFlags0;
    private boolean[] shuffledFlags1;

    private boolean[] sortedFlags0;
    private boolean[] sortedFlags1;


    @Setup
    public void init() {
        sortedFlags0 = new boolean[SIZE];
        Arrays.fill(sortedFlags0, 0, (SIZE * 7) / 10, true);
        sortedFlags1 = Arrays.copyOf(sortedFlags0, SIZE);

        shuffledFlags0 = Utils.shuffledCopyOf(sortedFlags0);
        shuffledFlags1 = Utils.shuffledCopyOf(sortedFlags1);
    }

    public static int countConditional(boolean[] f0, boolean[] f1) {
        int cnt = 0;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                if (f0[i] && f1[j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static int countLogical(boolean[] f0, boolean[] f1) {
        int cnt = 0;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                if (f0[i] & f1[j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(SIZE * SIZE)
    public int countConditionalShuffled() {
        return countConditional(shuffledFlags0, shuffledFlags1);
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(SIZE * SIZE)
    public int countConditionalSorted() {
        return countConditional(sortedFlags0, sortedFlags1);
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(SIZE * SIZE)
    public int countLogicalShuffled() {
        return countLogical(shuffledFlags0, shuffledFlags1);
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(SIZE * SIZE)
    public int countLogicalSorted() {
        return countLogical(sortedFlags0, sortedFlags1);
    }

}
