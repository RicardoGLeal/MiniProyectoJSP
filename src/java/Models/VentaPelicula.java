/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Propietario
 */
public class VentaPelicula {
    private int id;
    private int precio;
    private int idUser;
    private int peliculaId;
    public Pelicula pelicula;

    public VentaPelicula(int id, int precio, int idUser, int peliculaId, Pelicula pelicula) {
        this.id = id;
        this.precio = precio;
        this.idUser = idUser;
        this.peliculaId = peliculaId;
        this.pelicula = pelicula;
    }
    
    public VentaPelicula(int precio, int idUser, int peliculaId) {
        this.id = -1;
        this.precio = precio;
        this.idUser = idUser;
        this.peliculaId = peliculaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(int peliculaId) {
        this.peliculaId = peliculaId;
    }
    
    
}
