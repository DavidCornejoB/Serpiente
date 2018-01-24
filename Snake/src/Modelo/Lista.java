/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Eduardo
 */
public class Lista {
     Nodo lista;  

    public Nodo getLista() {  
        return lista;
    }

    public void setLista(Nodo lista) { 
        this.lista = lista;
    }

   public void agregarFinal(Nodo n) {  
        Nodo aux = lista;
        if (lista != null) {
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = n;
        } else {
            n.sig = lista;
            lista = n;
        }
    }

    public int obtenerCodigo() {
        int c = 0;
        Nodo aux = lista;
        if (lista == null) {
        } else {
            while (aux != null) {
                c++;
                aux = aux.sig;
            }
        }
        return c + 1;
    }
}
