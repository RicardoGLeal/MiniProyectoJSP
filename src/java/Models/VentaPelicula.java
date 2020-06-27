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
    private float precio;
    private int idUser;
    private int peliculaId;

    public VentaPelicula(int id, float precio, int idUser, int peliculaId) {
        this.id = id;
        this.precio = precio;
        this.idUser = idUser;
        this.peliculaId = peliculaId;
    }
    
      public VentaPelicula(float precio, int idUser, int peliculaId) {
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
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
