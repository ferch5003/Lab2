/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recorrido;

import java.util.Scanner;

/**
 *
 * @author Fernando Visbal
 */
public class Recorrido {

    public static int fActual;
    public static int cActual;
    public static int posicion;

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int n = 0;
        System.out.println("Digite el tamaño de la matriz cuadrada que se va a generar");
        System.out.println("Recuerde, que para el recorrido no se puede hacer una matriz con 0,1,2");
        while (n < 3) {
            n = leer.nextInt();
        }
        Tablero tablero = new Tablero(n);
        fActual = 0;
        cActual = 0;
        tablero.setTablero(fActual, cActual, 1);
        tablero.setSwitche(fActual, cActual, 1);
        recorrido(tablero, n);
    }

    public static void recorrido(Tablero tablero, int n) {
        posicion = 1;
        int suma = 0, cont = 0;
        boolean euler = true;
        while (euler) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    vista(tablero, n);
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    suma = suma + tablero.getTablero(i, j);
                }
                if (suma == 260) {
                    cont++;
                    break;
                }
            }
            System.out.println("Resultado:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print("|" + tablero.getTablero(i, j) + "|");
                }
                System.out.println("");
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    suma = suma + tablero.getTablero(j, i);
                }
                if (suma == 260) {
                    cont++;
                    break;
                }
            }
            if (cont == 2) {
                euler = false;
            } else {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        tablero.setSwitche(i, j, 0);
                        tablero.setTablero(i, j, 0);
                    }
                }
                suma = 0;
                posicion = 1;
                fActual = 0;
                cActual = 0;
                tablero.setTablero(fActual, cActual, 1);
                tablero.setSwitche(fActual, cActual, 1);
            }
        }
    }

    public static void vista(Tablero tablero, int n) {
        int uno = 1;
        int dos = 2;
        int fFinal;
        int cFinal;
        boolean salir = false;
        for (int k = 0; k < 2; k++) {
            for (int l = 0; l < 2; l++) {
                for (int m = 0; m < 2; m++) {
                    if (k == 0) {
                        salir = movimiento(tablero, dos, uno, n);
                        uno = uno *(-1);
                        if (salir) {
                            break;
                        }
                    } else {
                        salir = movimiento(tablero, uno, dos, n);
                        dos = dos * (-1);
                        if (salir) {
                            break;
                        }
                    }
                }
                if (salir) {
                    break;
                }
                if (k == 0) {
                    dos = dos * (-1);
                } else {
                    uno = uno * (-1);
                }
            }
            if (salir) {
                break;
            }
            uno = 1;
            dos = 2;
        }
    }

    public static boolean movimiento(Tablero tablero, int valor1, int valor2, int n) {
        int fFinal = fActual + valor1;
        int cFinal = cActual + valor2;
        if ((fFinal >= 0 && fFinal < n) && (cFinal >= 0 && cFinal < n)) {
            if (tablero.getSwitche(fFinal, cFinal) != 1) {
                posicion++;
                fActual = fFinal;
                cActual = cFinal;
                tablero.setSwitche(fFinal, cFinal, 1);
                tablero.setTablero(fFinal, cFinal, posicion);
                return true;
            }
        }
        return false;
    }

}
