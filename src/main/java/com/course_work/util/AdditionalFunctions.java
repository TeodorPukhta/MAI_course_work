package com.course_work.util;

import java.text.DecimalFormat;

public class AdditionalFunctions {
    public static int getIndexOfMax(double[] array) {
        if (array == null || array.length == 0) return -1;

        int max = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[max]) max = i;
        }
        return max;
    }
    public static int getIndexOfMin(double[] array) {
        if (array == null || array.length == 0) return -1;

        int min = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[min]) min = i;
        }
        return min;
    }

    public static String setDoubleFormatInArray(double[] array){
        DecimalFormat df = new DecimalFormat("0.000");
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            s.append(df.format(array[i])).append(" | ");
        }
        return s.toString();
    }
}
