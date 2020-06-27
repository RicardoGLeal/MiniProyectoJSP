
package servletPackage;

import Models.Libro;
import Models.Pelicula;
import Models.Videojuego;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ricardo González Leal
 * Clase BaseDeDatos
 * Esta función cuenta con un constructor y con todas las funciones necesarias
 * para poder realizar una conexión hacia la base de datos direccionesbd, 
 * autenticarse y realizar todas las operaciones de un crud para la tabla 
 * Direccion. 
 */
public class DBController {
    
    private Connection con;
    
    public DBController() {
        try {
            //se instancia el driver mysql jdbc.
            Class.forName("com.mysql.jdbc.Driver");
            //se realiza la conexión hacia la bd, especificando la ruta, el 
            //usuario y la contaseña.
            con = DriverManager.getConnection("jdbc:mysql://localhost/ventas", "root", "");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error al cargar el driver" + ex.getMessage());
        }
    }
    
   
    
    public boolean insertarPelicula(Pelicula p){
        
        try{
            String sql = "INSERT INTO pelicula(nombre,año,categoria,director,recaudacion) VALUES (?,?,?,?,?)" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, p.getNombre());
            stmt.setInt(2, p.getAño());
            stmt.setString(3, p.getCategoria());
            stmt.setString(4, p.getDirector());
            stmt.setFloat(5, p.getRecaudacion());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public boolean actualizarPelicula(Pelicula p){
        
        try{
            String sql = "UPDATE pelicula SET nombre=?,año=?,categoria=?,director=?,recaudacion=? WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, p.getNombre());
            stmt.setInt(2, p.getAño());
            stmt.setString(3, p.getCategoria());
            stmt.setString(4, p.getDirector());
            stmt.setFloat(5, p.getRecaudacion());
            
            stmt.setInt(6, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
      
    public boolean eliminarPelicula(Pelicula p){
          try{
            String sql = "DELETE FROM pelicula WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public ArrayList<Pelicula> obtenerPeliculas(){
        ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
        ResultSet result;
       
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM pelicula");   
            result = stmt.executeQuery();
            
            while(result.next()){
                Pelicula p = new Pelicula(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getInt("año"),
                        result.getString("categoria"),
                        result.getString("director"),
                        result.getFloat("recaudacion")
                );
                
                peliculas.add(p);
            }
            
        }catch(SQLException e){
            System.out.println("Error en la consulta");
            return null;
        }
        
        return peliculas;
    }
    
      
    public boolean insertarLibro(Libro p){
        
        try{
            String sql = "INSERT INTO libro(titulo,autor,sinopsis,año,editorial) VALUES (?,?,?,?,?)" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getAutor());
            stmt.setString(3, p.getSinopsis());
            stmt.setInt(4, p.getAño());
            stmt.setString(5, p.getEditorial());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public boolean actualizarLibro(Libro p){
        
        try{
            String sql = "UPDATE libro SET titulo=?,autor=?,sinopsis=?,año=?,editorial=? WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getAutor());
            stmt.setString(3, p.getSinopsis());
            stmt.setInt(4, p.getAño());
            stmt.setString(5, p.getEditorial());
            
            stmt.setInt(6, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
      
    public boolean eliminarLibro(Libro p){
          try{
            String sql = "DELETE FROM libro WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public ArrayList<Libro> obtenerLibro(){
        ArrayList<Libro> libros = new ArrayList<Libro>();
        ResultSet result;
       
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM libro");   
            result = stmt.executeQuery();
            
            while(result.next()){
                Libro p = new Libro(
                        result.getInt("id"),
                        result.getString("titulo"),
                        result.getInt("año"),
                        result.getString("autor"),
                        result.getString("sinopsis"),
                        result.getString("editorial")
                );
                
                libros.add(p);
            }
            
        }catch(SQLException e){
            System.out.println("Error en la consulta");
            return null;
        }
        
        return libros;
    }
    
    
    public boolean insertarVideojuego(Videojuego p){
        
        try{
            String sql = "INSERT INTO videojuego(titulo,desarrollador,distribuidora,clasificacion,año) VALUES (?,?,?,?,?)" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getDesarrollador());
            stmt.setString(3, p.getDistribuidora());
            stmt.setString(4, p.getClasificacion());
            stmt.setInt(5, p.getAño());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public boolean actualizarVideojuego(Videojuego p){
        
        try{
            String sql = "UPDATE videojuego SET titulo=?,desarrollador=?,distribuidora=?,clasificacion=?,año=? WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getDesarrollador());
            stmt.setString(3, p.getDistribuidora());
            stmt.setString(4, p.getClasificacion());
            stmt.setInt(5, p.getAño());
            
            stmt.setInt(6, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
      
    public boolean eliminarVideojuego(Videojuego p){
          try{
            String sql = "DELETE FROM videojuego WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public ArrayList<Videojuego> obtenerVideojuego(){
        ArrayList<Videojuego> videojuegos = new ArrayList<Videojuego>();
        ResultSet result;
       
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM videojuego");   
            result = stmt.executeQuery();
            
            while(result.next()){
                Videojuego p = new Videojuego(
                        result.getInt("id"),
                        result.getString("titulo"),
                        result.getInt("año"),
                        result.getString("desarrollador"),
                        result.getString("distribuidora"),
                        result.getString("clasificacion")
                );
                
                videojuegos.add(p);
            }
            
        }catch(SQLException e){
            System.out.println("Error en la consulta");
            return null;
        }
        
        return videojuegos;
    }
    
    
    public boolean selectUser(String user, String password) {
        ResultSet result;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT userid FROM usuario WHERE usuario = ? and password = ?");
            stmt.setString(1, user);
            stmt.setString(2, password);
            result = stmt.executeQuery();
            if(!result.first())
                return false;
                
        } catch (SQLException e) {
            System.out.println("No se encontraron resultados");
            return false;
        }
        return true;
    }
}
