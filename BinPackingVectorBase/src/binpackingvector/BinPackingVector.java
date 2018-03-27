/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpackingvector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ESTU-5K
 */
public class BinPackingVector {
public static int binSize;
public static int numElem;
public static ArrayList<int[]> bin;
public static ArrayList<int[]> elem;
public static int [] vectorSize;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner lea = new Scanner(System.in);
        elem = new ArrayList<>();//ArrayList de vectores
        bin = new ArrayList<>();//ArrayList de contenedores
        System.out.println("Digite el tamaño de los contenedores");
        binSize=lea.nextInt();//Tamaño máximo del contenedor
        System.out.println("Digite el número de elementos a guardar");
        numElem=lea.nextInt();//Número de vectores que se guardaran en contenedores
        vectorSize = new int[numElem];//Vector que guarda los peso de cada vector
        generarListaElemento();
        //Se imprimen los vectores
        for (int i = 0; i < elem.size(); i++) {
            System.out.println(Arrays.toString(elem.get(i)));
        }
        ingresarElemento();
        //Se impreme como quedan los contenedores luego de haber ingresado los vectores
        System.out.println("Número de contenedores mínimo="+bin.size());
        for (int i = 0; i < bin.size(); i++) {
            System.out.println(Arrays.toString(bin.get(i)));
        }
    }
    //Subrutina que ingresa los vectores en un contenedor
    /*
        Si dentro de algun contenedor hay espacio para el elemento i, este entra en alguno de esos contenedores
        Sino, se crea un nuevo contenedor y se ingresa el vector en este.
        
        El ingreso se hace de forma de que el primer elemento creado es el primero que entra y así en orden, 
        hasta tener todos los elementos ingresados
    
    */
    public static void ingresarElemento(){
        //Se crea el primer contenedor por defecto vacío con un espacio binSize, ya antes digitado por el usuario
        bin.add(new int[binSize]);
        /*
        Se recorre el ArrayList de vectores, para verificar si el vector i
        puede ingresar en los contenedores ya creados, o si toca crear un 
        nuevo contenedor y ahí ingresar el vector i
        */
        for (int i = 0; i < elem.size(); i++) {
            /*
            Aqui se obtiene la posicion dentro del ArrayList de contenedores, en la cual
            el vector i puede ingresar. Esta posición dicta en que contenedor, el vector i,
            se ingresara
            */
            int binPos=posDisponible(elem.get(i));
            /*
            El siguiente condicional verifica si la posición es igual a la de un contenedor existente
            o si es mayor
            
            Si es una posición exitente se añade el vector en ese contenedor
            Sino crea un nuevo contenedor y añade el vector en ese contenedor
            */
            if(binPos>=bin.size()){
                //Crea un nuevo vector, con tamaño igual al binSize
                int [] temp=new int[binSize];
                /*
                Como los vectores de Enteros por defecto queda llenos de 0.
                Entonces se ingresan los elementos en desde la posición 0
                hasta la posición final del vector
                */
                for (int j = 0; j < elem.get(i).length; j++) {
                    temp[j]=elem.get(i)[j];
                }
                //Se agrega un nuevo contenedor al ArrayList de Contenedor
                bin.add(temp);
            }
            else{
                //Se copia el contenido del contenedor en la posicion obtenida de binPos
                int [] temp=bin.get(binPos);
                //Se busca en que contenedor puede entrar el vector i
                int tope=conteoElem(temp);
                /*
                Si el tope=0:
                    Quiere decir que el contenedor esta vacío(Esta caso es para el primer ingreso de un vector)
                    Entonces ingresara el vector en contenedor vacío.
                Sino:
                    Quiere decir que el contenedor no esta vacío, entonces el contenedor ingresar los
                    datos del vector desde el tope hasta su número de elementos.
                */
                if(tope==0){
                    for (int j = 0; j < elem.get(i).length; j++) {
                        temp[j]=elem.get(i)[j];
                    }
                }
                else{
                    for (int j = 0; j < elem.get(i).length; j++) {
                        //Se copia la información del vector deseado, dentro del vector del contenedor disponible, desde la posici�n en donde haya espacio
                        //
                        temp[tope] = elem.get(i)[j];
                        tope++;
                    }
                }
                /*Se intercambia el contenido del contenedor por uno que tenga 
                 lo anterior+el vector i
                */
                
            }
        }
        
    }
    /*
    Esta subrutina se encarga de generar el ArrayList de vectores
    Se pide el número de vectores y de cada elemento cuanto es su peso(Cantidad de elementos)
    Los elementos se crearan de forma automatica, es decir si son 3 vectores con peso 2,3 y 5 respectivamente
    Entonces los vectores creador por el sistema serán:
    [1,1](Primer vector con 2 elementos)
    [2,2,2](Segundo vector con 3 elementos)
    [3,3,3,3,3](Quinto vector con 5 elementos)
    */
    public static ArrayList<int[]> generarListaElemento(){
        Scanner lea = new Scanner(System.in);
        //Se crea un vector que guarde los tamaños de cada vector
        for (int i = 0; i < vectorSize.length; i++) {
            System.out.println("Digite el peso(peso<=tamaño del bin) del vector "+(i+1));
            vectorSize[i]=lea.nextInt();
            //Si el peso del vector es mayor al binSize, entonces se vuelve a pedir el peso
            while(vectorSize[i]>binSize){
                System.out.println("Digite el peso(peso<=tamaño del bin) del vector "+(i+1));
                vectorSize[i]=lea.nextInt();
            }
            /*
            Se genera el contenido de cada vector, dependiendo del orden en que fue
            digitato y su peso
            */
            int [] temp = new int[vectorSize[i]];
            for (int j = 0; j < temp.length; j++) {
                temp[j]=i+1;
            }
            //Se añada al ArrayList de vectores para luego permitir sus uso
            elem.add(temp);
        }
        return elem;
    }
    /*
    Cuenta el número de elementos diferentes a 0 del vector que entra como parametro
    */
    public static int conteoElem(int [] elem){
        int cont=0;
        int i=0;
        while(i<elem.length){
            if(elem[i]!=0){
                //Debe aumentar el conteo de elementos
                cont++;
            }
            i++;
        }
        return cont;
    }
    
    /*
    Verifica en cual contenedor cabe el vector que entra como parametro
    Luego retorna en que contenedor se debe guardar
    */
    public static int posDisponible(int [] elem){
        boolean siHay=false;
        int i=0;
        while(!siHay && i<bin.size()){
            if(conteoElem(elem)+conteoElem(bin.get(i))<=binSize){
                //Si hay espacio se hace algo para que salga del ciclo 
                break;
            }
            else{
                i++;
            }
        }
        return i;
    }
    
}
