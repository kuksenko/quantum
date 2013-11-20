package quantum.demo7;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.logic.Control;

import java.util.concurrent.TimeUnit;

/**
 * Quantum Performance Effects (demo)
 *
 * demo7 - How much does communication cost?
 *
 * hint: microbenchmarks should be affinited to different pair of CPUs.
 *
 * e.g.  taskset -c 1,3 java -jar microbenchmarks.jar  ".*PintPong0.*" -f
 *       taskset -c 1,2 java -jar microbenchmarks.jar  ".*PintPong0.*" -f
 *       taskset -c ...
 *
 * @author Sergey Kuksenko
 */
@State(Scope.Group)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class PingPong0 {

    private boolean flag = true;

    /*
     * Dishonest and unreliable microbenchmark.
     * 'ping' and 'pong' may not see 'flag' modification
     */
    @GenerateMicroBenchmark
    @Group("shared_memory")
    public void ping(Control c) {
        while (flag & !c.stopMeasurement) ;
        flag = true;
    }

    @GenerateMicroBenchmark
    @Group("shared_memory")
    public void pong(Control c) {
        while (!flag & !c.stopMeasurement) ;
        flag = false;
    }

    private volatile boolean vflag = true;

    @GenerateMicroBenchmark
    @Group("Volatile")
    public void vping(Control c) {
        while (vflag & !c.stopMeasurement) ;
        vflag = true;
    }

    @GenerateMicroBenchmark
    @Group("Volatile")
    public void vpong(Control c) {
        while (!vflag & !c.stopMeasurement) ;
        vflag = false;
    }

}
