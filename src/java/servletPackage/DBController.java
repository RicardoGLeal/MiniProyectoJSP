package servletPackage;

import Models.Libro;
import Models.Pelicula;
import Models.VentaLibro;
import Models.VentaPelicula;
import Models.VentaVideojuego;
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
    
    public boolean eliminarPelicula(int p){
          try{
            String sql = "DELETE FROM pelicula WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p);
            
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
    
    public Pelicula obtenerPelicula(int id){
        ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>(obtenerPeliculas());
        
        for(int i = 0; i<peliculas.size(); i++){
            if(peliculas.get(i).getId() == id)
                return peliculas.get(i);
        }
        
        return null;
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
    
    public boolean eliminarLibro(int p){
          try{
            String sql = "DELETE FROM libro WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p);
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public ArrayList<Libro> obtenerLibros(){
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
    
    public Libro obtenerLibro(int id){
        ArrayList<Libro> libros = new ArrayList<Libro>(obtenerLibros());
        
        for(int i = 0; i<libros.size(); i++){
            if(libros.get(i).getId() == id)
                return libros.get(i);
        }
        
        return null;
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
    
    public boolean eliminarVideojuego(int p){
          try{
            String sql = "DELETE FROM videojuego WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p);
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public ArrayList<Videojuego> obtenerVideojuegos(){
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
    
    public Videojuego obtenerVideojuego(int id){
        ArrayList<Videojuego> videojuegos = new ArrayList<Videojuego>(obtenerVideojuegos());
        
        for(int i = 0; i<videojuegos.size(); i++){
            if(videojuegos.get(i).getId() == id)
                return videojuegos.get(i);
        }
        
        return null;
    }
    
    
    public boolean insertarVentaLibro(VentaLibro p){
        
        try{
            String sql = "INSERT INTO venta_libro(libroid,userid,precio) VALUES (?,?,?)" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p.getLibroId());
            stmt.setInt(2, p.getIdUser());
            stmt.setInt(3, p.getPrecio());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public boolean actualizarVentaLibro(VentaLibro p){
        
        try{
            String sql = "UPDATE venta_libro SET libroid=?,userid=?,precio=? WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p.getLibroId());
            stmt.setInt(2, p.getIdUser());
            stmt.setInt(3, p.getPrecio());
            
            stmt.setInt(4, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
      
    public boolean eliminarVentaLibro(VentaLibro p){
          try{
            String sql = "DELETE FROM venta_libro WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public boolean eliminarVentaLibro(int p){
          try{
            String sql = "DELETE FROM venta_libro WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p);
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public ArrayList<VentaLibro> obtenerVentaLibros(){
        ArrayList<VentaLibro> ventaLibros = new ArrayList<VentaLibro>();
        ResultSet result;
       
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM venta_libro");   
            result = stmt.executeQuery();
            
            while(result.next()){
                
                int id = result.getInt("libroid");
                Libro libro = obtenerLibro(id);
                
                VentaLibro p = new VentaLibro(
                        result.getInt("id"),
                        result.getInt("precio"),
                        result.getInt("userid"),
                        result.getInt("libroid"),
                        libro
                );
                
                ventaLibros.add(p);
            }
            
        }catch(SQLException e){
            System.out.println("Error en la consulta");
            return null;
        }
        
        return ventaLibros;
    }
    
    public VentaLibro obtenerVentaLibro(int id){
        ArrayList<VentaLibro> ventas = new ArrayList<VentaLibro>(obtenerVentaLibros());
        
        for(int i = 0; i<ventas.size(); i++){
            if(ventas.get(i).getId() == id)
                return ventas.get(i);
        }
        
        return null;
    }
    
    public ArrayList<VentaLibro> obtenerVentaLibroUser(int id){
        ArrayList<VentaLibro> ventas = new ArrayList<VentaLibro>(obtenerVentaLibros());
        ArrayList<VentaLibro> v = new ArrayList<VentaLibro>();
        
        for(int i = 0; i<ventas.size(); i++){
            if(ventas.get(i).getIdUser() == id)
                v.add(ventas.get(i));
        }
        
        return v;
    }
    
    
    public boolean insertarVentaPelicula(VentaPelicula p){
        
        try{
            String sql = "INSERT INTO venta_pelicula(peliculaid,userid,precio) VALUES (?,?,?)" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p.getPeliculaId());
            stmt.setInt(2, p.getIdUser());
            stmt.setInt(3, p.getPrecio());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public boolean actualizarVentaPelicula(VentaPelicula p){
        
        try{
            String sql = "UPDATE venta_pelicula SET peliculaid=?,userid=?,precio=? WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p.getPeliculaId());
            stmt.setInt(2, p.getIdUser());
            stmt.setInt(3, p.getPrecio());
            
            stmt.setInt(4, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
      
    public boolean eliminarVentaPelicula(VentaPelicula p){
          try{
            String sql = "DELETE FROM venta_pelicula WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public boolean eliminarVentaPelicula(int p){
          try{
            String sql = "DELETE FROM venta_pelicula WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p);
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public ArrayList<VentaPelicula> obtenerVentaPeliculas(){
        ArrayList<VentaPelicula> ventaPeliculas = new ArrayList<VentaPelicula>();
        ResultSet result;
       
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM venta_pelicula");   
            result = stmt.executeQuery();
            
            while(result.next()){
                
                int peliId = result.getInt("peliculaid");
                Pelicula peli = obtenerPelicula(peliId);
                
                VentaPelicula p = new VentaPelicula(
                        result.getInt("id"),
                        result.getInt("precio"),
                        result.getInt("userid"),
                        result.getInt("peliculaid"),
                        peli
                );
                
                ventaPeliculas.add(p);
            }
            
        }catch(SQLException e){
            System.out.println("Error en la consulta");
            return null;
        }
        
        return ventaPeliculas;
    }
    
    public VentaPelicula obtenerVentaPelicula(int id){
        ArrayList<VentaPelicula> ventas = new ArrayList<VentaPelicula>(obtenerVentaPeliculas());
        
        for(int i = 0; i<ventas.size(); i++){
            if(ventas.get(i).getId() == id)
                return ventas.get(i);
        }
        
        return null;
    }
    
    public ArrayList<VentaPelicula> obtenerVentaPeliculaUser(int id){
        ArrayList<VentaPelicula> ventas = new ArrayList<VentaPelicula>(obtenerVentaPeliculas());
        ArrayList<VentaPelicula> v = new ArrayList<VentaPelicula>();
        
        for(int i = 0; i<ventas.size(); i++){
            if(ventas.get(i).getIdUser() == id)
                v.add(ventas.get(i));
        }
        
        return v;
    }
    
    
    public boolean insertarVentaVideoJuego(VentaVideojuego p){
        
        try{
            String sql = "INSERT INTO venta_videojuego(videojuegoid,userid,precio) VALUES (?,?,?)" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p.getVideojuegoId());
            stmt.setInt(2, p.getIdUser());
            stmt.setInt(3, p.getPrecio());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public boolean actualizarVentaVideoJuego(VentaVideojuego p){
        
        try{
            String sql = "UPDATE venta_videojuego SET videojuegoid=?,userid=?,precio=? WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p.getVideojuegoId());
            stmt.setInt(2, p.getIdUser());
            stmt.setInt(3, p.getPrecio());
            
            stmt.setInt(4, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
      
    public boolean eliminarVentaVideoJuego(VentaVideojuego p){
          try{
            String sql = "DELETE FROM venta_videojuego WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public boolean eliminarVentaVideoJuego(int p){
          try{
            String sql = "DELETE FROM venta_videojuego WHERE id=?" ; 
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, p);
            
            stmt.executeUpdate();
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public ArrayList<VentaVideojuego> obtenerVentaVideoJuegos(){
        ArrayList<VentaVideojuego> ventaVideojuegos = new ArrayList<VentaVideojuego>();
        ResultSet result;
       
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM venta_videojuego");   
            result = stmt.executeQuery();
            
            while(result.next()){
                
                int id = result.getInt("videojuegoid");
                Videojuego videojuego = obtenerVideojuego(id);               
                
                VentaVideojuego p = new VentaVideojuego(
                        result.getInt("id"),
                        result.getInt("precio"),
                        result.getInt("userid"),
                        result.getInt("videojuegoid"),
                        videojuego
                );
                
                ventaVideojuegos.add(p);
            }
            
        }catch(SQLException e){
            System.out.println("Error en la consulta");
            return null;
        }
        
        return ventaVideojuegos;
    }
    
    public VentaVideojuego obtenerVentaVideoJuego(int id){
        ArrayList<VentaVideojuego> ventas = new ArrayList<VentaVideojuego>(obtenerVentaVideoJuegos());
        
        for(int i = 0; i<ventas.size(); i++){
            if(ventas.get(i).getId() == id)
                return ventas.get(i);
        }
        
        return null;
    }
    
    public ArrayList<VentaVideojuego> obtenerVentaVideoJuegoUser(int id){
        ArrayList<VentaVideojuego> ventas = new ArrayList<VentaVideojuego>(obtenerVentaVideoJuegos());
        ArrayList<VentaVideojuego> v = new ArrayList<VentaVideojuego>();
        
        for(int i = 0; i<ventas.size(); i++){
            if(ventas.get(i).getIdUser() == id)
                v.add(ventas.get(i));
        }
        
        return v;
    }
    
    
    public int selectUser(String user, String password) {
        ResultSet result;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT userid FROM usuario WHERE usuario = ? and password = ?");
            stmt.setString(1, user);
            stmt.setString(2, password);
            result = stmt.executeQuery();
            if (!result.first()) {
                return 0;
            } else {
                return result.getInt("userid");
            }
        } catch (SQLException e) {
            System.out.println("No se encontraron resultados");
            return 0;
        }
    }
}