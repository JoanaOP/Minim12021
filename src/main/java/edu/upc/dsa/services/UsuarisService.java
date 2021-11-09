package edu.upc.dsa.services;

import edu.upc.dsa.EmptyList;
import edu.upc.dsa.Manager;
import edu.upc.dsa.ManagerImpl;

import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.Usuari;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/usuaris", description = "Endpoint to usuaris Service")
@Path("/usuaris")
public class UsuarisService {

    private Manager manager;

    public UsuarisService() {
        this.manager = ManagerImpl.getInstance();

        PuntoInteres miPunto1 = new PuntoInteres("Casilla 2, Casilla 3, Porta 2");
        PuntoInteres miPunto2 = new PuntoInteres("Casilla 4, Casilla -2, Porta 7");

        List<PuntoInteres> puntsjoana = new LinkedList<PuntoInteres>();
        puntsjoana.add(miPunto1);
        puntsjoana.add(miPunto2);

        PuntoInteres miPunto3 = new PuntoInteres("Casilla 1, Casilla 2, Porta 8");
        List<PuntoInteres> puntsjordi = new LinkedList<PuntoInteres>();
        puntsjordi.add(miPunto3);
        puntsjordi.add(miPunto2);
        puntsjordi.add(miPunto1);

        Usuari joana = new Usuari("Joana","22222222X",puntsjoana);
        Usuari jordi = new Usuari("Jordi","33333333Y",puntsjordi);
        Usuari aida = new Usuari("Aida","11111111Z",new LinkedList<PuntoInteres>());

        manager.añadirUsuario(joana);
        manager.añadirUsuario(jordi);
        manager.añadirUsuario(aida);


    }

    @POST
    @ApiOperation(value = "añadir usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuari.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/usuari")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response añadirUsuario(Usuari usuari) {

        if(usuari.getUsuariID()==null || usuari.getNomUsuari()==null){
            return Response.status(500).entity(usuari).build();
        }
        else{
            this.manager.añadirUsuario(usuari);
            return Response.status(201).entity(usuari).build();
        }
    }


    @GET
    @ApiOperation(value = "ordenar usuaris alfabeticament ", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuari.class, responseContainer="List"),
    })
    @Path("/listaOrdenadaAlfabeticamente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuariosOrdenadosAlfabeticamente() throws EmptyList {

        List<Usuari> llistaOrdenada = manager.ordenarUsuarisAlfabeticament();

        GenericEntity<List<Usuari>> entity = new GenericEntity<List<Usuari>>(llistaOrdenada) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get info usuari", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuari.class),
            @ApiResponse(code = 404, message = "Usuari not found")
    })
    @Path("/usuari/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoUsuari(@PathParam("id") String id) {
        Usuari u = this.manager.getInfoUsuari(id);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
    }

    @POST
    @ApiOperation(value = "añadir punto interes a un usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=PuntoInteres.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/usuari/{id}/puntoInteres")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response añadirPuntoInteres(PuntoInteres puntoInteres, @PathParam("id") String id) {

        if(puntoInteres.getPunto()==null){
            return Response.status(500).entity(puntoInteres).build();
        }
        else{
            this.manager.añadirPuntoInteres(puntoInteres,id);
            return Response.status(201).entity(puntoInteres).build();
        }
    }


    @GET
    @ApiOperation(value = "get Puntos interes Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntoInteres.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/usuari/{id}/puntosInteres")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPuntosInteresUsuario(@PathParam("id") String id) throws EmptyList {
        Usuari usuari = manager.getInfoUsuari(id);
        if (usuari == null){
            return Response.status(404).build();
        }
        else{
            List<PuntoInteres> puntosInteresUsuari = manager.getPuntosInteresUsuari(id);
            GenericEntity<List<PuntoInteres>> entity = new GenericEntity<List<PuntoInteres>>(puntosInteresUsuari) {};
            return Response.status(201).entity(entity).build();
        }
    }

    @GET
    @ApiOperation(value = "get usuarios de un Punto interes", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuari.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "PuntoInteres not found")
    })
    @Path("/puntosInteres/{punto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarisPuntoInteres(@PathParam("punto") String punto) throws EmptyList {

        if (punto == null){
            return Response.status(404).build();
        }
        else{
            PuntoInteres miPunto = new PuntoInteres(punto);
            List<Usuari> usuarisList = manager.getUsuarisPuntoInteres(miPunto);
            GenericEntity<List<Usuari>> entity = new GenericEntity<List<Usuari>>(usuarisList) {};
            return Response.status(201).entity(entity).build();
        }
    }

    @GET
    @ApiOperation(value = "ordenar usuaris per quantitat de punts interes ", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuari.class, responseContainer="List"),
    })
    @Path("/listaOrdenadaPorPuntosInteres")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarisOrdenatsPerPuntsInteres() throws EmptyList {

        List<Usuari> llistaOrdenada = manager.ordenarUsuariosPorCantidadPuntos();

        GenericEntity<List<Usuari>> entity = new GenericEntity<List<Usuari>>(llistaOrdenada) {};
        return Response.status(201).entity(entity).build();
    }


}
