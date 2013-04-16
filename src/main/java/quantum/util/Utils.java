package quantum.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Quantum Performance Effects, utility methods
 *
 * Is source code the best documentation? It is an open question.
 * At least, source code is the most precise documentation.
 *
 * @author Sergey Kuksenko
 */
public class Utils {

    public static double[] newRandomDoubleArray(int size) {
        double[] arr = new double[size];
        fill(arr);
        return arr;
    }

    public static int[] newRandomIntArray(int size) {
        int[] arr = new int[size];
        fill(arr);
        return arr;
    }

    public static int[] sortedCopyOf(int[] array) {
        int[] newArray = Arrays.copyOf(array, array.length);
        Arrays.sort(newArray);
        return newArray;
    }

    public static <T> T[] sortedCopyOf(T[] original, Comparator<? super T> c) {
        T[] newArray = Arrays.copyOf(original, original.length);
        Arrays.sort(newArray, c);
        return newArray;
    }

    public static int[] shuffledCopyOf(int[] array) {
        int[] newArray = Arrays.copyOf(array, array.length);
        shuffle(newArray);
        return newArray;
    }

    public static boolean[] shuffledCopyOf(boolean[] array) {
        boolean[] newArray = Arrays.copyOf(array, array.length);
        shuffle(newArray);
        return newArray;
    }

    public static <T> T[] shuffledCopyOf(T[] original) {
        T[] newArray = Arrays.copyOf(original, original.length);
        Collections.shuffle(Arrays.asList(newArray));
        return newArray;
    }

    private static void shuffle(int[] array) {
        Random rnd = new Random(0xBAD_BEE);
        for (int i = array.length; i > 1; i--)
            swap(array, i-1, rnd.nextInt(i));

    }

    private static void swap(int[] array, int i0, int i1) {
        int x = array[i0];
        array[i0] = array[i1];
        array[i1] = x;
    }

    private static void shuffle(boolean[] array) {
        Random rnd = new Random(0xBAD_BEE);
        for (int i = array.length; i > 1; i--)
            swap(array, i-1, rnd.nextInt(i));

    }

    private static void swap(boolean[] array, int i0, int i1) {
        boolean x = array[i0];
        array[i0] = array[i1];
        array[i1] = x;
    }

    private static void fill(int[] array) {
        Random rnd = new Random(0xBAD_BEE);
        for(int i = 0; i<array.length; i++) {
            array[i] = rnd.nextInt();
        }
    }

    private static void fill(double[] array) {
        Random rnd = new Random(0xBAD_BEE);
        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextDouble();
        }
    }


}
