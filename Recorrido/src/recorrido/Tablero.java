/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recorrido;

/**
 *
 * @author Fernando Visbal
 */
public class Tablero {

    public int fila;
    public int columna;
    public int[][] switche = new int[100][100];
    public int[][] tablero = new int [100][100];

    public Tablero(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tablero[i][j] = 0;
                this.switche[i][j] = 0;
            }
        }
    }

    public int getSwitche(int f, int c) {
        return switche[f][c];
    }

    public void setSwitche(int f, int c,int valor) {
        switche[f][c] = valor;
    }

    public int getTablero(int f, int c) {
        return tablero[f][c];
    }

    public void setTablero(int f, int c, int posicion) {
        tablero[f][c] = posicion;
    }
}


