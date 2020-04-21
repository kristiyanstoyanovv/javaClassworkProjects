package com.company;

public class Main {

    public static void main(String[] args) {
        double aSide = 2;
        double bSide = 6;
        System.out.println("P = " + calculateP(aSide, bSide));
        System.out.println("S = " + calculateS(aSide, bSide));
    }
    public static double calculateP(double a, double b) {
        double P = 2*a + 2*b;
        return P;
    }
    public static double calculateS(double a, double b) {
        double S = a*b;
        return S;
    }
}