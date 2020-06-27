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
public class VentaLibro {
    private int id;
    private int precio;
    private int idUser;
    private int libroId;
    public Libro libro;

    public VentaLibro(int id, int precio, int idUser, int libroId, Libro libro) {
        this.id = id;
        this.precio = precio;
        this.idUser = idUser;
        this.libroId = libroId;
        this.libro = libro;
    }
    
    public VentaLibro(int precio, int idUser, int libroId) {
        this.id = -1;
        this.precio = precio;
        this.idUser = idUser;
        this.libroId = libroId;
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

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }
    
    public Libro getLibro(){
        return libro;
    }
    
}
