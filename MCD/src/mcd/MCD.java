/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcd;

import java.util.Scanner;
import java.math.BigInteger;

/**
 *
 * @author lenovo
 */
public class MCD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Digite el valor de m:");
        BigInteger m = leer.nextBigInteger();
        System.out.println("Digite el valor de n:");
        BigInteger n = leer.nextBigInteger();
        long startTime = System.nanoTime();
        System.out.println("Resultado con mcd Euclides: " + Euclides(m, n));
        System.out.println("Tiempo de ejecucion del mcd Euclides: " + (System.nanoTime() - startTime));
        long startTime2 = System.nanoTime();
        System.out.println("Resultado en mcd Recursivo: " + mcdRecursivo(m, n));
        System.out.println("Tiempo de ejecucion del mcd Recursivo: " + (System.nanoTime() - startTime2));
        long startTime3 = System.nanoTime();
        System.out.println("Resultado en mcd normal: " + mcdNormal(m, n));
        System.out.println("Tiempo de ejecucion del mcd normal: " + (System.nanoTime() - startTime3));

    }

    public static BigInteger mcdNormal(BigInteger m, BigInteger n) {
        BigInteger i = (min(m, n)).add(BigInteger.ONE);
        do {
            i = i.subtract(BigInteger.ONE);
        } while ((n.mod(i)).compareTo(BigInteger.ZERO) != 0 || (m.mod(i)).compareTo(BigInteger.ZERO) != 0);
        return i;
    }

    public static BigInteger Euclides(BigInteger m, BigInteger n) {
        while (m.compareTo(BigInteger.ZERO) > 0) {
            BigInteger t = m;
            m = n.mod(m);
            n = t;
        }
        return n;
    }

    public static BigInteger mcdRecursivo(BigInteger m, BigInteger n) {
        if (m.compareTo(BigInteger.ZERO) == 0) {
            return n;
        } else {
            return mcdRecursivo(n.mod(m), m);
        }
    }

    public static BigInteger min(BigInteger m, BigInteger n) {
        return m.compareTo(n) >= 0 ? m : n;
    }
}
