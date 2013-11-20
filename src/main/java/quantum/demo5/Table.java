package quantum.demo5;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import quantum.util.Utils;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/**
 * Quantum Performance Effects (demo)
 *
 * demo5 - Exploring Branch Predictor:
 *   - conditional jumps
 *   - indirect jumps
 *   - HotSpot's optimizations (Cache Inline)
 *
 * @author Sergey Kuksenko
 */
@State
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class Table {

    public interface I {
        public int amount();
    }

    public static class C0 implements I {
        @Override
        public int amount() {
            return 0;
        }
    }

    public static class C1 implements I {
        @Override
        public int amount() {
            return 1;
        }
    }

    public static class C2 implements I {
        @Override
        public int amount() {
            return 2;
        }
    }

    public static class C3 implements I {
        @Override
        public int amount() {
            return 3;
        }
    }

    private static I[] fillRegular(int receivers) {
        I[] data = new I[SIZE];
        for (int i = 0; i < SIZE; i++) {
            switch (i % receivers) {
                case 0:
                    data[i] = new C0();
                    break;
                case 1:
                    data[i] = new C1();
                    break;
                case 2:
                    data[i] = new C2();
                    break;
                case 3:
                    data[i] = new C3();
                    break;
            }
        }
        return data;
    }


    public static final int SIZE = 2 * 1024;

    private I[] regular1;

    private I[] regular2;
    private I[] sorted2;
    private I[] shuffled2;

    private I[] regular3;
    private I[] sorted3;
    private I[] shuffled3;

    private I[] regular4;
    private I[] sorted4;
    private I[] shuffled4;

    @Setup
    public void init() {
        regular1 = fillRegular(1);

        regular2 = fillRegular(2);
        shuffled2 = Utils.shuffledCopyOf(regular2);
        sorted2 = Utils.sortedCopyOf(regular2, CMP);

        regular3 = fillRegular(3);
        shuffled3 = Utils.shuffledCopyOf(regular3);
        sorted3 = Utils.sortedCopyOf(regular3, CMP);

        regular4 = fillRegular(4);
        shuffled4 = Utils.shuffledCopyOf(regular4);
        sorted4 = Utils.sortedCopyOf(regular4, CMP);

    }

    private static final Comparator<I> CMP = new Comparator<I>() {
        @Override
        public int compare(I o1, I o2) {
            return Integer.compare(o1.amount(), o2.amount());
        }
    };

    public static int sum(I[] a) {
        int s = 0;
        for (I i : a) {
            s += i.amount();
        }
        return s;
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int receive1_regular() {
        return sum(regular1);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int receive2_regular() {
        return sum(regular2);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int receive2_sorted() {
        return sum(sorted2);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int receive2_shuffled() {
        return sum(shuffled2);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int receive3_regular() {
        return sum(regular3);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int receive3_sorted() {
        return sum(sorted3);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int receive3_shuffled() {
        return sum(shuffled3);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int receive4_regular() {
        return sum(regular4);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int receive4_sorted() {
        return sum(sorted4);
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int receive4_shuffled() {
        return sum(shuffled4);
    }

    public static int sumUnrolledBy2(I[] a) {
        int s = 0;
        for (int i = 0; i < SIZE; i += 2) {
            s += a[i].amount();
            s += a[i + 1].amount();
        }
        return s;
    }

    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int receive3_unrolledRegular() {
        return sumUnrolledBy2(regular3);
    }

    /*
     * Try this!
     * Compare results with microbenchmarks above.
     * Could you explain results?
     * Hint: "type profile"
     */
    @GenerateMicroBenchmark
    @OperationsPerInvocation(SIZE)
    public int receive4_unrolledRegular() {
        return sumUnrolledBy2(regular4);
    }

}
