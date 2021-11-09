package edu.upc.dsa;


import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.Usuari;

import java.util.List;

public interface Manager {

    public void añadirUsuario(Usuari usuari);
    public List<Usuari> ordenarUsuarisAlfabeticament();
    public Usuari getInfoUsuari(String usuariId);
    public void añadirPuntoInteres(PuntoInteres punto, String usuariId);
    public List<PuntoInteres> getPuntosInteresUsuari(String usuariId);
    public List<Usuari> getUsuarisPuntoInteres(PuntoInteres punto);
    public List<Usuari> ordenarUsuariosPorCantidadPuntos();


    /*
    public List<Producto> ordenarProductosPrecio() throws EmptyList; //TEST FET
    public void realizarPedido(Comanda comanda); //TEST FET
    public Comanda servirPedido(); //TEST FET
    public List<Comanda> listadoPedidosUser(String usuariID) throws EmptyList; //TEST FET
    public List<Producto> ordenarProductosVentas() throws  EmptyList; //TEST FET
    public void añadirProductoLista(Producto producto);
    public void añadirUsuario(Usuari usuari);

    public int getNumProductos();
    public int getNumUsuarios();

     */
}
