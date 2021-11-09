package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Usuari {

    List<PuntoInteres> llistaPuntosInteres;
    String usuariID;
    String nomusuari;

    public Usuari(){
    };

    public Usuari (String nomusuari, String usuariID, List<PuntoInteres> llistaPuntosInteres) {
        this();
        this.setNomUsuari(nomusuari);
        this.setUsuariID(usuariID);
        this.setLlistaPuntosInteres(llistaPuntosInteres);
    }


    public String getNomUsuari(){

        return this.nomusuari;
    }
    public void setNomUsuari(String nom) {
        this.nomusuari = nom;
    }

    public String getUsuariID(){
        return this.usuariID;
    }

    public void setUsuariID(String usuariID){
        this.usuariID = usuariID;
    }

    public List<PuntoInteres> getLlistaPuntosInteres(){
        return this.llistaPuntosInteres;
    }

    public void setLlistaPuntosInteres(List<PuntoInteres> llistaPuntosInteres) {
        this.llistaPuntosInteres = llistaPuntosInteres;
    }

    public void addPuntoInteres(PuntoInteres punto){
        this.llistaPuntosInteres.add(punto);
    }

    public int getCantidadPuntosInteres(){
        return this.llistaPuntosInteres.size();
    }

}
