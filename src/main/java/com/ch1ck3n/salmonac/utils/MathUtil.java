package com.ch1ck3n.salmonac.utils;

public class MathUtil {
    public static float getVlFromFloat(float f) {
        float f1 = Float.parseFloat(String.format("%.1f", Math.abs(f)));
        return Math.max(f1, 0.1f);
    }
    public static float getVlFromDouble(double d) {
        float f1 = Float.parseFloat(String.format("%.1f", Math.abs(d)));
        return Math.max(f1, 0.1f);
    }

    public static float getVlFromFloatOrDefault(float f, float defaultVl) {
        float f1 = Float.parseFloat(String.format("%.1f", Math.abs(f)));
        return Math.max(f1, defaultVl);
    }
    public static float getVlFromDoubleOrDefault(double d, float defaultVl) {
        float f1 = Float.parseFloat(String.format("%.1f", Math.abs(d)));
        return Math.max(f1, defaultVl);
    }

    public static String getInfoFromFloat10(float f) {
        return String.format("%.10f", f);
    }
    public static String getInfoFromDouble10(double d) {
        return String.format("%.10f", d);
    }

    public static String getInfoFromFloat3(float f) {
        return String.format("%.3f", f);
    }
    public static String getInfoFromDouble3(double d) {
        return String.format("%.3f", d);
    }
}
