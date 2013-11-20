package quantum.demo1;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import quantum.util.Utils;

/**
 * Quantum Performance Effects (demo)
 *
 * demo1 - Exploring ILP bounds
 *
 * @author Sergey Kuksenko
 */
@State
public class DoubleSumStep0 {

    public static final int SIZE = 2048;

    private double[] array;

    @Setup
    public void init() {
        array = Utils.newRandomDoubleArray(SIZE);
    }

    @GenerateMicroBenchmark
    public double test1() {
        double sum = 0.0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    @GenerateMicroBenchmark
    public double testManualUnroll() {
        double sum = 0.0;
        for (int i = 0; i < array.length; i += 4) {
            sum += array[i] + array[i + 1] + array[i + 2] + array[i + 3];
        }
        return sum;
    }

}
