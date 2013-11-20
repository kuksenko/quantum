package quantum.demo6;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

/**
 * Quantum Performance Effects (demo)
 *
 * demo6 - How does SMT share Functional Units?
 *
 * hint:
 *  - the microbenchmark should be affinited to other logical CPU
 *  - microbenchmark from the quantum.demo6.MapGet class should be affinited to one logical CPU
 *  - look over different logical CPUs pairs and explain results.
 *
 * @author Sergey Kuksenko
 */
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class Hyper {

    public static double d0 = 1173.1;
    public static double d1 = 233.2;
    public static double d2 = 67.3;

    @GenerateMicroBenchmark
    @OperationsPerInvocation(5)
    public double troll() {
        return (d0 / d2) / ((d1 / d2) / (d0 / d1));
    }

}
