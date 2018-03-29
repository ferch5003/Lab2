/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuadradomagico;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Fernando Visbal
 */
public class CuadradoMagico {

    public static ArrayList<Integer> valores = new ArrayList<Integer>();
    public static Tablero matriz;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int n = 2;
        while (n % 2 == 0) {
            System.out.println("Digite el tamaño de la matriz cuadrada impar: ");
            n = leer.nextInt();
        }
        int nCuadrada = n * n;
        int sumatoria = (((nCuadrada + 1) * nCuadrada) / 2);
        armarNumeros(nCuadrada);
        matriz = new Tablero(n);
        armarMatriz(n);
        BigInteger resultado1 = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matriz.getTablero(i, j) + "|");
                if (i == 0) {
                    resultado1 = resultado1.add(BigInteger.valueOf(matriz.getTablero(i, j)));
                }
            }
            System.out.println("");
        }
        System.out.println("El resultado de cada suma debe dar: " + resultado1);
    }

    public static void armarNumeros(int n) {
        for (int i = 0; i < n; i++) {
            valores.add(i + 1);
        }
    }

    public static void armarMatriz(int n) {
        int mediana = (n / 2);
        int f = 0;
        int c = mediana;
        matriz.setTablero(f, mediana, valores.get(0));
        matriz.setSwitche(f, mediana, 1);
        for (int i = 1; i < n * n; i++) {
            if (f - 1 < 0 && c + 1 < n) {
                f = n - 1;
                c++;
                matriz.setTablero(f, c, valores.get(i));
                matriz.setSwitche(f, c, 1);
            } else if (c + 1 > n - 1 && f - 1 >= 0) {
                f--;
                c = 0;
                matriz.setTablero(f, c, valores.get(i));
                matriz.setSwitche(f, c, 1);
            } else if (f - 1 < 0 && c + 1 > n - 1) {
                f++;
                matriz.setTablero(f, c, valores.get(i));
                matriz.setSwitche(f, c, 1);
            } else if ((f - 1 >= 0 && c + 1 < n)) {
                if (matriz.getSwitche(f - 1, c + 1) != 1) {
                    f--;
                    c++;
                    matriz.setTablero(f, c, valores.get(i));
                    matriz.setSwitche(f, c, 1);
                } else {
                    f++;
                    matriz.setTablero(f, c, valores.get(i));
                    matriz.setSwitche(f, c, 1);
                }
            }
        }
    }
}
