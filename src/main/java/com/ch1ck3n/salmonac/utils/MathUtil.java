package com.ch1ck3n.salmonac.utils;

public class MathUtil {
    public static float getVlFromFloat(float f) {
        return Float.parseFloat(String.format("%.1f", f));
    }

    public static float getVlFromDouble(double d) {
        return Float.parseFloat(String.format("%.1f", d));
    }
}
