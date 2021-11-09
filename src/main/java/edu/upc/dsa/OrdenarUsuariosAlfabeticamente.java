package edu.upc.dsa;

import edu.upc.dsa.models.Usuari;

import java.util.Comparator;

public class OrdenarUsuariosAlfabeticamente implements Comparator<Usuari> {
    public int compare(Usuari u1, Usuari u2){

        return u1.getNomUsuari().compareTo(u2.getNomUsuari());
    }
}
