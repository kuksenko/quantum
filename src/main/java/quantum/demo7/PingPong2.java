package quantum.demo7;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Control;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Quantum Performance Effects (demo)
 *
 * demo7 - How much does communication cost?
 *
 * hint: microbenchmarks should be affinited to different pairs of CPUs.
 *
 * e.g.  taskset -c 1,3 java -jar benchmarks.jar  ".*PingPong2.*"
 *       taskset -c 1,2 java -jar benchmarks.jar  ".*PingPong2.*"
 *       taskset -c ...
 *
 * @author Sergey Kuksenko
 */
@State(Scope.Group)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class PingPong2 {

    private boolean flag = true;

    public synchronized boolean isFlag() {
        return flag;
    }

    public synchronized void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Benchmark
    @Group("monitor")
    public void mping(Control c) {
        while (isFlag() & !c.stopMeasurement) ;
        setFlag(true);
    }

    @Benchmark
    @Group("monitor")
    public void mpong(Control c) {
        while (!isFlag() & !c.stopMeasurement) ;
        setFlag(false);
    }

    public boolean isFlag(Lock lock) {
        lock.lock();
        try {
            return flag;
        } finally {
            lock.unlock();
        }
    }

    public void setFlag(Lock lock, boolean flag) {
        lock.lock();
        try {
            this.flag = flag;
        } finally {
            lock.unlock();
        }
    }

    private Lock lock = new ReentrantLock();

    @Benchmark
    @Group("reentrant")
    public void rping(Control c) {
        while (isFlag(lock) & !c.stopMeasurement) ;
        setFlag(lock, true);
    }

    @Benchmark
    @Group("reentrant")
    public void rpong(Control c) {
        while (!isFlag(lock) & !c.stopMeasurement) ;
        setFlag(lock, false);
    }

    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    @Benchmark
    @Group("rwlock")
    public void rwping(Control c) {
        while (isFlag(rwLock.readLock()) & !c.stopMeasurement) ;
        setFlag(rwLock.writeLock(), true);
    }

    @Benchmark
    @Group("rwlock")
    public void rwpong(Control c) {
        while (!isFlag(rwLock.readLock()) & !c.stopMeasurement) ;
        setFlag(rwLock.writeLock(), false);
    }

}
