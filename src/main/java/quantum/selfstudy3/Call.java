package quantum.selfstudy3;

import org.openjdk.jmh.annotations.BenchmarkType;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;

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
@CompilerControl(CompilerControl.Mode.DONT_INLINE)
public class Call {

    public static int foo00() {
        return 1;
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(1)
    public static int foo01() {
        return foo00();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(2)
    public static int foo02() {
        return foo01();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(3)
    public static int foo03() {
        return foo02();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(4)
    public static int foo04() {
        return foo03();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(5)
    public static int foo05() {
        return foo04();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(6)
    public static int foo06() {
        return foo05();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(7)
    public static int foo07() {
        return foo06();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(8)
    public static int foo08() {
        return foo07();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(9)
    public static int foo09() {
        return foo08();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(10)
    public static int foo10() {
        return foo09();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(11)
    public static int foo11() {
        return foo10();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(12)
    public static int foo12() {
        return foo11();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(13)
    public static int foo13() {
        return foo12();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(14)
    public static int foo14() {
        return foo13();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(15)
    public static int foo15() {
        return foo14();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(16)
    public static int foo16() {
        return foo15();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(17)
    public static int foo17() {
        return foo16();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(18)
    public static int foo18() {
        return foo17();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(19)
    public static int foo19() {
        return foo18();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @OperationsPerInvocation(20)
    public static int foo20() {
        return foo19();
    }


}
