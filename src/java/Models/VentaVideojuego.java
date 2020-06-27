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
public class VentaVideojuego {
    private int id;
    private int precio;
    private int idUser;
    private int videojuegoId;
    public Videojuego videojuego;

    public VentaVideojuego(int id, int precio, int idUser, int videojuegoId, Videojuego videojuego) {
        this.id = id;
        this.precio = precio;
        this.idUser = idUser;
        this.videojuegoId = videojuegoId;
        this.videojuego = videojuego;
    }
    
    public VentaVideojuego(int precio, int idUser, int videojuegoId) {
        this.id = -1;
        this.precio = precio;
        this.idUser = idUser;
        this.videojuegoId = videojuegoId;
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

    public int getVideojuegoId() {
        return videojuegoId;
    }

    public void setVideojuegoId(int videojuegoId) {
        this.videojuegoId = videojuegoId;
    }
    
    public Videojuego getVideoJuego(){
        return videojuego;
    }

}
