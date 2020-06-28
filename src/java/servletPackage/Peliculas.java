/**
 *
* @author Ricardo-Angel-Erick Clase Peliculas Esta clase es el servlet
 * utilizado para el manejo del crud de peliculas y del sistema de venta de
 * peliculas. En el doGet de este servlet, se verifica que haya un usuario
 * logueado, de lo contrario lo redirecciona a el login para que inicie sesión.
 * Este servlet conecta con cuatro diferentes JSP utilizados para el sistema,
 * uno para mostrar un listado de peliculas, otro para dar de alta uno
 * nuevo, otro para venderlo y otro para mostrar un listado de los peliculas
 * vendidos. La manera en la que se realiza la comunicación de información es
 * por medio de parámetros utilizados en inputs y botones en forms.
 */

package servletPackage;

import Models.Pelicula;
import Models.VentaPelicula;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Peliculas", urlPatterns = {"/Peliculas"})
public class Peliculas extends HttpServlet {

    DBController con;

    public Peliculas() {
        con = new DBController();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        con = new DBController();
        this.doGet(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = (HttpSession) request.getSession();
        boolean loggued = false;

        try {
            int userid = Integer.parseInt(session.getAttribute("id").toString());
            loggued = true;
        } catch (NullPointerException e) {
            loggued = false;
            response.sendRedirect("Logout");
        }
        if (loggued) {
            try {
                ShowPeliculas(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Peliculas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method. Esta función se ejecuta cuando
     * en una vista existe un form que tiene como Action este controlador y
     * tiene como método post.     
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String link = request.getParameter("link");

            if (link == null) {
                ShowPeliculas(request, response);
            } else {
                String[] splitedLink = link.split("/");
                switch (splitedLink[0]) {
                    case "Agregar":
                        showCreate(request, response);
                        break;
                    case "Insert":
                        insertPelicula(request, response);
                        break;
                    case "Edit":
                        showEditForm(request, response, Integer.parseInt(splitedLink[1]));
                        break;
                    case "Update":
                        update(request, response, Integer.parseInt(splitedLink[1]));
                        break;
                    case "Delete":
                        Delete(request, response, Integer.parseInt(splitedLink[1]));
                        break;
                    case "Sell":
                        Sell(request, response, Integer.parseInt(splitedLink[1]));
                        break;
                    case "Sold":
                        Sold(request, response, Integer.parseInt(splitedLink[1]));
                        break;
                    case "Mis peliculas vendidas":
                        misPelisVendidas(request, response);
                        break;
                    default:
                        ShowPeliculas(request, response);
                        break;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Peliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Función showCreate. Se manda llamar cuando se da click en el botón de
     * 'Agregar', el cual desde la vista index.jsp le manda al controlador la
     * palabra 'Agregar', la cual es reconocida en el GET y se llama a esta
     * función, la cual se encarga de cargar la vista del formulario para
     * agregar una pelicula.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void showCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {//To change body of generated methods, choose Tools | Templates.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pelicula/create.jsp");
        dispatcher.forward(request, response);
    }

    
 
    
     /**
     * Función insertPelicula.
     * Se manda llamar cuando se da click en el botón de Guardar, el cual desde
     * la vista le manda al controlador la palabra "Insert", posteriormente es
     * reconocida en el GET y se llama a esta función.
     * La vista también almacena los valores del pelicula, los cuales en
     * esta función se reciben por medio de request.getParameter para finalmente
     * realizar el insert en la base de datos.
     * @param request servlet request
     * @param response servlet response
     * @throws SQLException
     * @throws IOException 
     */
    private void insertPelicula(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nombre = request.getParameter("nombre");
        int año = Integer.parseInt(request.getParameter("ano"));
        String categoria = request.getParameter("categoria");
        String director = request.getParameter("director");
        float recaudacion = Float.parseFloat(request.getParameter("recaudacion"));
        con.insertarPelicula(new Pelicula(nombre,año,categoria,director,recaudacion));
        response.sendRedirect("Peliculas");
    }

    /**
     * Función Delete Esta función se manda llamar desde el GET, cuando
     * se da click en el botón de 'eliminar' en alguna de las peliculas.
     * Cuando se da click en el botón de 'eliminar' la vista le manda al
     * controlador la palabra "Delete" más el ID de la pelicula, posteriormente
     * estos datos son reconocidos en el GET y se llama a esta función, la cual
     * manda llamar a la función que ejecuta la query de eliminar una pelicula.
     * @param request servlet request
     * @param response servlet response
     * @param id ID de la pelicula a eliminar.
     * @throws IOException
     * @throws SQLException
     */
    private void Delete(HttpServletRequest request, HttpServletResponse response, int id) 
            throws IOException, SQLException{
        con.eliminarPelicula(id);
        response.sendRedirect("Peliculas");

    }

    /**
     * Función showEditForm. Se manda llamar cuando se da click en el botón de
     * 'Editar' de una pelicula, el cual desde la vista index.jsp le manda al
     * controlador la palabra 'Edit' más el id de la pelicula a editar, estos
     * parámetros son reconocidos en el GET y de ahí se llama a esta función, la
     * cual se encarga de cargar la vista del formulario para editar la
     * pelicula, mostrando los datos preecargados que tiene la pelicula a
     * editar, esto gracias a que al atributo 'direccion' de la vista se le
     * asigna la pelicula correspondiente al id recibido por medio de una
     * consulta al controlador de la bd.
     *
     * @param request servlet request
     * @param response servlet response
     * @param id id de la direccion
     * @throws IOException
     * @throws SQLException
     * @throws ServletException
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response, int id)
            throws IOException, SQLException, ServletException {
        Pelicula pelicula = con.obtenerPelicula(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pelicula/create.jsp");
        request.setAttribute("pelicula", pelicula);
        dispatcher.forward(request, response);
        
    }

    /**
     * Función Update.
     * Se encarga de modificar los datos de un videojuego en la base de datos.
     * Los nuevos valores los recibe por parte del jsp de create y por medio de
     * parámetros.
     * @param request servlet request
     * @param response servlet response
     * @param id id de la pelicula.
     * @throws IOException
     * @throws SQLException
     * @throws ServletException 
     */
    private void update(HttpServletRequest request, HttpServletResponse response, int id)
            throws IOException, SQLException, ServletException {
       String nombre = request.getParameter("nombre");
        int año = Integer.parseInt(request.getParameter("ano"));
        String categoria = request.getParameter("categoria");
        String director = request.getParameter("director");
        float recaudacion = Float.parseFloat(request.getParameter("recaudacion"));
        con.actualizarPelicula(new Pelicula(id, nombre, año, categoria, director, recaudacion));
        response.sendRedirect("Peliculas");
    }

    /**
     * Función ShowPeliculas.
     * Se encarga de realizar la consulta de obtener todos los registros de 
     * las peliculas en la base de datos y de mandárselas a la vista por
     * medio de la función request.setAttribute.
     * @param request servlet request
     * @param response servlet response
     * @throws SQLException error en la consulta.
     * @throws IOException error IO.
     * @throws ServletException error en el servlet.
     */
    private void ShowPeliculas(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        List<Pelicula> peliculas = con.obtenerPeliculas();
        request.setAttribute("listPeliculas", peliculas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pelicula/index.jsp");
        dispatcher.forward(request, response);
    }

     /**
     * Función Sell
     * Esta función se manda llamar cuando el usuario da click en vender en 
     * alguna de las peliculas, y lo que ésta función hace es cargar el jsp
     * de sell, obtener los datos de la pelicula correspondiente a vender, y 
     * mandarlos al jsp y mostrarlos por medio de parámetros.
     * @param request
     * @param response
     * @param id id de la pelicula.
     * @throws IOException
     * @throws ServletException 
     */
    private void Sell(HttpServletRequest request, HttpServletResponse response, int id) throws IOException, ServletException {
        Pelicula pelicula = con.obtenerPelicula(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pelicula/sell.jsp");
        request.setAttribute("pelicula", pelicula);
        dispatcher.forward(request, response);

    }

    /**
     * Función Sold
     * Se manda llamar cuando el usuario vende un videojuego.
     * Lo que hace es obtener el precio por medio de parámetro y el id del 
     * usuario por medio de sessión.getAttribute. Posteriormente realiza un 
     * insert en la tabla venta_videojuego.
     * @param request
     * @param response
     * @param peliculaID id de la pelicula
     * @throws IOException 
     */
    private void Sold(HttpServletRequest request, HttpServletResponse response, int peliculaID) throws IOException, ServletException {
        int precio = Integer.parseInt(request.getParameter("precio"));
        HttpSession session = (HttpSession) request.getSession();
        int userid = Integer.parseInt(session.getAttribute("id").toString());
        con.insertarVentaPelicula(new VentaPelicula(precio, userid, peliculaID));
        misPelisVendidas(request, response);
    }
    
    /**
     * Función misPelisVendidas
     * Esta función se manda llamar cuando el usuario da click en el botón de
     * 'mis peliculas vendidos', en este se muestra el id de venta, el nombre
     * de la pelicula y el precio de la pelicula de cada uno de las peliculas
     * vendidas por el usuario logueado.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void misPelisVendidas(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        
        HttpSession session = (HttpSession) request.getSession();
        int userid = Integer.parseInt(session.getAttribute("id").toString());
        List<VentaPelicula> peliculas = con.obtenerVentaPeliculaUser(userid);
        
        request.setAttribute("listPeliculas", peliculas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pelicula/mispeliculas.jsp");
        dispatcher.forward(request, response);
    }
}
