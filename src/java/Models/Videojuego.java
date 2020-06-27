/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author erick
 */
public class Videojuego {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora = distribuidora;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    
    private Integer id;
    private String titulo;
    private Integer año;
    private String desarrollador;
    private String distribuidora;
    private String clasificacion;
  
    public Videojuego(String titulo, Integer año, String desarrollador, String distribuidora, String clasificacion)
    {
        this.id = id;
        this.titulo = titulo;
        this.año = año;
        this.desarrollador = desarrollador;
        this.distribuidora = distribuidora;
        this.clasificacion = clasificacion;
    }
    
    public Videojuego(Integer id, String titulo, Integer año, String desarrollador, String distribuidora, String clasificacion)
    {
        this.titulo = titulo;
        this.año = año;
        this.desarrollador = desarrollador;
        this.distribuidora = distribuidora;
        this.clasificacion = clasificacion;
    }
    
    @Override
    public String toString() { 
        return "ID: " + id +"   Título: " + titulo +"  Desarrollador: "+desarrollador + "  Distribuidora: " + distribuidora + "  Año: " + año + "  Clasificacion: " + clasificacion;
    }
    
    public Videojuego()
    {
        this.titulo = "sin titulo";
        this.año = 0;
        this.desarrollador = "sin desarrollador";
        this.distribuidora = "sin distribuidora";
        this.clasificacion = "sin clasificacion";
    }
}
