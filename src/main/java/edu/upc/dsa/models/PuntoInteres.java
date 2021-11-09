package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class PuntoInteres {

    private String punto;

    public PuntoInteres(){
    };

    public PuntoInteres(String punto) { // Constructor
        this();
        this.setPunto(punto);
    }


    public String getPunto() {
        return punto;
    }

    public void setPunto(String punto) {
        this.punto = punto;
    }

}
