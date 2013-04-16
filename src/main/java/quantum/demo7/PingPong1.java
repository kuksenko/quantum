package quantum.demo7;

import org.openjdk.jmh.annotations.BenchmarkType;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.logic.Control;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Quantum Performance Effects (demo)
 *
 * demo7 - How much does communication cost?
 *
 * hint: microbenchmarks should be affinited to different pair of CPUs.
 *
 * e.g.  taskset -c 1,3 java -jar microbenchmarks.jar  ".*PintPong1.*" -f
 *       taskset -c 1,2 java -jar microbenchmarks.jar  ".*PintPong1.*" -f
 *       taskset -c ...
 *
 * @author Sergey Kuksenko
 */
@State(Scope.Group)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class PingPong1 {

    private AtomicBoolean flag_atomic = new AtomicBoolean(true);

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @Group("woodpecker")
    public void t2_ping(Control c) {
        // spinloop in the "Nuclear Woodpecker" style
        while (!flag_atomic.compareAndSet(false, true) & !c.stopMeasurement) ;

    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @Group("woodpecker")
    public void t2_pong(Control c) {
        while (!flag_atomic.compareAndSet(true, false) & !c.stopMeasurement) ;
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @Group("atomic")
    public void t3_ping(Control c) {
        // "normal" spinloop style
        while (!c.stopMeasurement) {
            while (flag_atomic.get() & !c.stopMeasurement) ;
            if (flag_atomic.compareAndSet(false, true)) {
                return;
            }
        }
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @Group("atomic")
    public void t3_pong(Control c) {
        while (!c.stopMeasurement) {
            while (!flag_atomic.get() & !c.stopMeasurement) ;
            if (flag_atomic.compareAndSet(true, false)) {
                return;
            }
        }
    }

}
