/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonacci;

import java.util.Scanner;
import java.math.BigInteger;

/**
 *
 * @author lenovo
 */
public class Fibonacci {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Hasta que numero va a terminar su serie de fibonacci:");
        int n = leer.nextInt();
        long startTime = System.nanoTime();
        System.out.println("Fibonacci iterativo: " + fibonacciIterativo(n));
        System.out.println("Tiempo de ejecucion del Fibonacci iterativo: " + (System.nanoTime() - startTime));
        long startTime2 = System.nanoTime();
        System.out.println("Fibonacci recursivo: " + fibonacciRecursivo(BigInteger.valueOf(n)));
        System.out.println("Tiempo de ejecucion del Fibonacci recursivo: " + (System.nanoTime() - startTime2));
    }

    public static BigInteger fibonacciRecursivo(BigInteger n) {
        BigInteger dos = new BigInteger("2");
        return n.compareTo(dos) < 0 ? n : fibonacciRecursivo(n.subtract(BigInteger.ONE)).add(fibonacciRecursivo(n.subtract(dos)));
    }

    public static BigInteger fibonacciIterativo(int n) {
        BigInteger i = BigInteger.ONE;
        BigInteger j = BigInteger.ZERO;
        for (int k = 0; k < n; k++) {
            j = i.add(j);
            i = j.subtract(i);
        }
        return j;
    }

}
