package com.ada.mortgage.util;

public class MortgageUtil {
    public static double power(double x, int p) {
        if (p == 0) return 1;
        if (p == 1) return x;
        return x * power(x, p - 1);
    }
}
