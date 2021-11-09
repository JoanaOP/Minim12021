package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ManagerTest {

    ManagerImpl manager = ManagerImpl.getInstance();

    Usuari joana;
    Usuari jordi;
    Usuari aida;

    PuntoInteres miPunto1;
    PuntoInteres miPunto2;

    /*
    Comanda comanda1;
    Comanda comanda2;
*/



    @Before
    public void setUp  () {

        miPunto1 = new PuntoInteres("Casilla 2, Casilla 3, Porta 2");
        miPunto2 = new PuntoInteres("Casilla 4, Casilla -2, Porta 7");

        List<PuntoInteres> puntsjoana = new LinkedList<PuntoInteres>();
        puntsjoana.add(miPunto1);
        puntsjoana.add(miPunto2);

        PuntoInteres miPunto3 = new PuntoInteres("Casilla 1, Casilla 2, Porta 8");
        List<PuntoInteres> puntsjordi = new LinkedList<PuntoInteres>();
        puntsjordi.add(miPunto3);
        puntsjordi.add(miPunto2);
        puntsjordi.add(miPunto1);

        joana = new Usuari("Joana","22222222X",puntsjoana);
        jordi = new Usuari("Jordi","33333333Y",puntsjordi);
        aida = new Usuari("Aida","11111111Z",new LinkedList<PuntoInteres>());

        manager.añadirUsuario(joana);
        manager.añadirUsuario(jordi);
        manager.añadirUsuario(aida);


    }

    @Test
    public void añadirUsuarioTest(){
        manager.añadirUsuario(new Usuari("Toni","55555555T",new LinkedList<PuntoInteres>()));
        Assert.assertEquals(4,manager.getNumUsuarios());
        Assert.assertEquals("Toni",manager.getUsuaris().get("55555555T").getNomUsuari());
    }
    @Test
    public void ordenarUsuarisAlfabeticamentTest(){
        List<Usuari> misUsuarios = new ArrayList<Usuari>();
        misUsuarios.add(aida);
        misUsuarios.add(joana);
        misUsuarios.add(jordi);
        Assert.assertEquals(misUsuarios, manager.ordenarUsuarisAlfabeticament());
    }
    @Test
    public void getInfoUsuariTest(){
        Assert.assertEquals(joana,manager.getInfoUsuari("22222222X"));
    }
    @Test
    public void añadirPuntoInteresTest(){
        PuntoInteres miPunto = new PuntoInteres("Casilla 2, Casilla 3, Porta 2");
        manager.añadirPuntoInteres(miPunto, joana.getUsuariID());
        Assert.assertEquals(3, joana.getCantidadPuntosInteres());
        Assert.assertEquals(miPunto,manager.getPuntosInteresUsuari(joana.getUsuariID()).get(joana.getCantidadPuntosInteres()-1));
    }
    @Test
    public void getPuntosInteresUsuariTest(){
        List<PuntoInteres> misPuntos = new LinkedList<PuntoInteres>();
        misPuntos.add(miPunto1);
        misPuntos.add(miPunto2);
        Assert.assertEquals(misPuntos,manager.getPuntosInteresUsuari(joana.getUsuariID()));
    }
    @Test
    public void getUsuarisPuntoInteresTest(){
        List<Usuari> misUsuarios = new ArrayList<Usuari>();
        misUsuarios.add(jordi);
        misUsuarios.add(joana);
        Assert.assertEquals(misUsuarios,manager.getUsuarisPuntoInteres(miPunto1));
    }
    @Test
    public void ordenarUsuariosPorCantidadPuntosTest(){
        List<Usuari> misUsuarios = new ArrayList<Usuari>();
        misUsuarios.add(jordi);
        misUsuarios.add(joana);
        misUsuarios.add(aida);
        Assert.assertEquals(misUsuarios,manager.ordenarUsuariosPorCantidadPuntos());
    }

    @After
    public void tearDown(){

        manager.borrarTot();

        joana = null;
        jordi = null;
        aida = null;

        miPunto1 = null;
        miPunto2 = null;


    }

}
