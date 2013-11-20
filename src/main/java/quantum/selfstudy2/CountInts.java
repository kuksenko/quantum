package quantum.selfstudy2;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Mode;
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
 * get and try to explain performance results
 *
 * @author Sergey Kuksenko
 */
@State
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class CountInts {

    public static final int SIZE = 4 * 1024;

    private int[] shuffledData0;
    private int[] shuffledData1;
    private int[] sortedData0;
    private int[] sortedData1;


    @Setup
    public void init() {
        sortedData0 = Utils.newRandomIntArray(SIZE);
        Arrays.fill(sortedData0, 0, (SIZE * 7) / 10, 0);

        sortedData1 = Utils.newRandomIntArray(SIZE);
        Arrays.fill(sortedData1, 0, (SIZE * 7) / 10, 0);

        shuffledData0 = Utils.shuffledCopyOf(sortedData0);
        shuffledData1 = Utils.shuffledCopyOf(sortedData1);
    }

    public static int count0(int[] f0, int[] f1) {
        int cnt = 0;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                if ((f0[i] == 0) && (f1[j] == 0)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static int count1(int[] f0, int[] f1) {
        int cnt = 0;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                if ((f0[i] == 0) & (f1[j] == 0)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static int count2(int[] f0, int[] f1) {
        int cnt = 0;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                if ((f0[i] | f1[j]) == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE * SIZE)
    public int testShuffled0() {
        return count0(shuffledData0, shuffledData1);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE * SIZE)
    public int testSorted0() {
        return count0(sortedData0, sortedData1);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE * SIZE)
    public int testShuffled1() {
        return count1(shuffledData0, shuffledData1);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE * SIZE)
    public int testSorted1() {
        return count1(sortedData0, sortedData1);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE * SIZE)
    public int testShuffled2() {
        return count2(shuffledData0, shuffledData1);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE * SIZE)
    public int testSorted2() {
        return count2(sortedData0, sortedData1);
    }

}
