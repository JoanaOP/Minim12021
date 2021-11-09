package edu.upc.dsa;

import edu.upc.dsa.models.Usuari;

import java.util.Comparator;

public class OrdenarUsuarisoPorCantidadPuntos implements Comparator<Usuari> {
    public int compare(Usuari u1, Usuari u2){
        return (int)(u1.getCantidadPuntosInteres() - u2.getCantidadPuntosInteres());
    }
}
