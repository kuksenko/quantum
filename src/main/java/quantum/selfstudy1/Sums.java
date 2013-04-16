package quantum.selfstudy1;

/**
 * Quantum Performance Effects (demo)
 *
 * @author Sergey Kuksenko
 */
public class Sums {

    public static double sum1(double[] data, int[] indexes) {
        double sum = 0.0;
        for (int i = 0; i < indexes.length; i++) {
            sum += data[indexes[i]];
        }
        return sum;
    }

    public static double sum2(double[] data, int[] indexes) {
        double sum0 = 0.0;
        double sum1 = 0.0;
        for (int i = 0; i < indexes.length; i += 2) {
            sum0 += data[indexes[i]];
            sum1 += data[indexes[i + 1]];
        }
        return sum0 + sum1;
    }

    public static double sum4(double[] data, int[] indexes) {
        double sum0 = 0.0;
        double sum1 = 0.0;
        double sum2 = 0.0;
        double sum3 = 0.0;
        for (int i = 0; i < indexes.length; i += 4) {
            sum0 += data[indexes[i]];
            sum1 += data[indexes[i + 1]];
            sum2 += data[indexes[i + 2]];
            sum3 += data[indexes[i + 3]];
        }
        return (sum0 + sum1) + (sum2 + sum3);

    }
}
