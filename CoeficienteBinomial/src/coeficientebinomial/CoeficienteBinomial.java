/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coeficientebinomial;

import java.util.Scanner;
import java.math.BigInteger;

/**
 *
 * @author Fernando Visbal
 */
public class CoeficienteBinomial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Digite el valor de n: ");
        BigInteger n = leer.nextBigInteger();
        System.out.println("Digite el valor de k: ");
        BigInteger k = leer.nextBigInteger();
        long startTime = System.nanoTime();
        System.out.println("El resultado del coeficiente binomial iterativo es: " + coeficienteIterativo(n, k));
        System.out.println("Tiempo de ejecucion del coeficiente binomial iterativo: " + (System.nanoTime() - startTime));
        long startTime2 = System.nanoTime();
        System.out.println("El resultado del coeficiente binomial recursivo es: " + coeficienteRecursivo(n, k));
                System.out.println("Tiempo de ejecucion del coeficiente binomial recursivo: " + (System.nanoTime() - startTime2));
    }

    public static BigInteger coeficienteIterativo(BigInteger n, BigInteger k) {
        if (k.compareTo(n) > 0) {
            return BigInteger.ZERO;
        }
        if (n.compareTo(k) == 0) {
            return BigInteger.ONE;
        }
        BigInteger factorial1 = BigInteger.ONE, factorial2 = BigInteger.ONE, factorial3 = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            factorial1 = factorial1.multiply(i);
        }
        for (BigInteger i = BigInteger.ONE; i.compareTo(k) <= 0; i = i.add(BigInteger.ONE)) {
            factorial2 = factorial2.multiply(i);
        }
        for (BigInteger i = BigInteger.ONE; i.compareTo(n.subtract(k)) <= 0; i = i.add(BigInteger.ONE)) {
            factorial3 = factorial3.multiply(i);
        }
        return factorial1.divide(factorial2.multiply(factorial3));
    }

    public static BigInteger coeficienteRecursivo(BigInteger n, BigInteger k) {
        if (n.compareTo(k) == 0 || k.compareTo(BigInteger.ZERO) == 0) {
            return BigInteger.ONE;
        }
        if (k.compareTo(n) > 0) {
            return BigInteger.ZERO;
        }
        if (k.compareTo(n.divide(BigInteger.valueOf(2))) > 0) {
            return coeficienteRecursivo(n, n.subtract(k));
        }
        return coeficienteRecursivo(n.subtract(BigInteger.ONE), k.subtract(BigInteger.ONE)).add(coeficienteRecursivo(n.subtract(BigInteger.ONE), k));
    }
}
