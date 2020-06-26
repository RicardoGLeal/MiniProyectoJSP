/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author ricar
 */
public class Pelicula {
    private Integer id;
    private String nombre;
    private Integer año;
    private String categoria;
    private String director;
    private Float recaudacion;

    public Pelicula(String nombre, Integer año, String categoria, String director, Float recaudacion) {
        this.nombre = nombre;
        this.año = año;
        this.categoria = categoria;
        this.director = director;
        this.recaudacion = recaudacion;
    }
    
     public Pelicula(Integer id,String nombre, Integer año, String categoria, String director, Float recaudacion) {
        this.id = id;
        this.nombre = nombre;
        this.año = año;
        this.categoria = categoria;
        this.director = director;
        this.recaudacion = recaudacion;
    }
    

    public Pelicula() {
        this.nombre = "sin nombre";
        this.año = 0;
        this.categoria = "sin categoria";
        this.director = "sin director";
        this.recaudacion = 0f;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Float getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(Float recaudacion) {
        this.recaudacion = recaudacion;
    }
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    @Override
    public String toString() { 
        return "ID: " + id +"   Nombre: " + nombre +"  Director: "+director + "  Categoria: " + categoria + "  Año: " + año + "  Recaudacion: " + recaudacion + " $";
    }
}
