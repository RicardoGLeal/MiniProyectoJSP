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
public class Libro {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    
    private int id;
    private String titulo;
    private int año;
    private String autor;
    private String sinopsis;
    private String editorial;
    
    public Libro(String titulo, Integer año, String autor, String sinopsis, String editorial)
    {
        this.titulo = titulo;
        this.año = año;
        this.autor = autor;
        this.sinopsis = sinopsis;
        this.editorial = editorial;
    }
    
    public Libro(Integer id, String titulo, Integer año, String autor, String sinopsis, String editorial)
    {
        this.id = id;
        this.titulo = titulo;
        this.año = año;
        this.autor = autor;
        this.sinopsis = sinopsis;
        this.editorial = editorial;
    }
    
    @Override
    public String toString() { 
        return "ID: " + id +"   Título: " + titulo +"  Autor: "+autor + "  Sinopsis: " + sinopsis + "  Año: " + año + "  Editorial: " + editorial;
    }
}
