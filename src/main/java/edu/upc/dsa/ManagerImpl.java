package edu.upc.dsa;


import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.util.*;

public class ManagerImpl implements Manager {


    private HashMap<String, Usuari> usuaris;

    private static ManagerImpl instance;

    final static Logger logger = Logger.getLogger(ManagerImpl.class);

    private ManagerImpl(){
        usuaris = new HashMap<String,Usuari>();
    }

    public static ManagerImpl getInstance(){
        if (instance == null){
            instance = new ManagerImpl();
        }
        return instance;
    }



    public Usuari getInfoUsuari(String usuariId){
        Usuari u = usuaris.get(usuariId);
        if(u==null){
            logger.error("No s'ha trobat el usuari amb id: "+usuariId);
        }
        else {
            logger.info("Informacio usuari, nom: " + u.getNomUsuari() + ", id: " + u.getUsuariID() + ".");
        }
        return u;
    }

    public void añadirUsuario(Usuari usuari){
        if(usuari == null){
            logger.error("No es pot afegir l'usuari");
        }
        else{
            this.usuaris.put(usuari.getUsuariID(),usuari);
            logger.info("Usuario "+usuari.getNomUsuari()+" añadido.");
        }

    }

    public List<Usuari> ordenarUsuarisAlfabeticament(){
        List<Usuari> llistaOrdenada = new ArrayList<Usuari>(usuaris.values());
        Collections.sort(llistaOrdenada, new OrdenarUsuariosAlfabeticamente());
        logger.info("Se han ordenado los productos por precio.");
        return llistaOrdenada;
    }

    public void añadirPuntoInteres(PuntoInteres punto, String usuariId){
        Usuari u = usuaris.get(usuariId);
        if((u == null) || (punto == null)){
            logger.error("Falta el punt o el usuari");
        }
        else {
            u.addPuntoInteres(punto);
            logger.info("S'ha afegit el punt d'interes " + punto.getPunto() + " al usuari " + u.getNomUsuari());
        }
    }

    public List<PuntoInteres> getPuntosInteresUsuari(String usuariId){
        if(usuariId == null){
            logger.error("Falta l'usuari id");
        }
        else {
            logger.info("S'han trobat els punts d'interes per els quals ha passat l'usuari " + usuaris.get(usuariId).getNomUsuari());
        }
        return this.usuaris.get(usuariId).getLlistaPuntosInteres();
    }

    public List<Usuari> getUsuarisPuntoInteres(PuntoInteres punto){
        List<Usuari> llistaUsuaris = new ArrayList<Usuari>(usuaris.values());
        List<Usuari> llistaUsuarisPunto = new ArrayList<Usuari>();
        for(Usuari u: llistaUsuaris) {
            for (PuntoInteres p : u.getLlistaPuntosInteres()) {
                if (p.getPunto().equals(punto.getPunto())) {
                    llistaUsuarisPunto.add(u);
                    logger.info("L'usuari "+u.getNomUsuari()+" ha passat pel punt.");
                }
            }
        }
        logger.info("S'ha trobat els usuaris que han passat pel punt: "+punto.getPunto());
        return llistaUsuarisPunto;
    }

    public List<Usuari> ordenarUsuariosPorCantidadPuntos(){
        List<Usuari> llistaOrdenada = new ArrayList<Usuari>(usuaris.values());
        Collections.sort(llistaOrdenada,new OrdenarUsuarisoPorCantidadPuntos().reversed());
        logger.info("Se han ordenado los usuarios segun la cantidad de puntos de interes.");
        return llistaOrdenada;
    }

    public int getNumUsuarios(){ return usuaris.size();}
    public HashMap<String, Usuari> getUsuaris(){
        return usuaris;
    }

    public void borrarTot(){
        usuaris.clear();
    }

}