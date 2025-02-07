/**
  @author Ricardo-Angel-Erick Clase Videojuego Esta clase es el servlet
 * utilizado para el manejo del crud de libros y del sistema de venta de
 * libros. En el doGet de este servlet, se verifica que haya un usuario
 * logueado, de lo contrario lo redirecciona a el login para que inicie sesión.
 * Este servlet conecta con cuatro diferentes JSP utilizados para el sistema,
 * uno para mostrar un listado de libros, otro para dar de alta uno
 * nuevo, otro para venderlo y otro para mostrar un listado de los libros
 * vendidos. La manera en la que se realiza la comunicación de información es
 * por medio de parámetros utilizados en inputs y botones en forms.
 */
package servletPackage;

import Models.Libro;
import Models.Pelicula;
import Models.VentaLibro;
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

@WebServlet(name = "Libros", urlPatterns = {"/Libros"})
public class Libros extends HttpServlet {

   
    DBController con;

    public Libros() {
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
                ShowLibros(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Peliculas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method. 
     * Esta función se ejecuta cuando
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
                ShowLibros(request, response);
            } else {
                String[] splitedLink = link.split("/");
                switch (splitedLink[0]) {
                    case "Agregar":
                        showCreate(request, response);
                        break;
                    case "Insert":
                        insertLibro(request, response);
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
                    case "Mis libros vendidos":
                        misLibrosVendidos(request, response);
                        break;
                    default:
                        ShowLibros(request, response);
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
     * agregar una dirección.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void showCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {//To change body of generated methods, choose Tools | Templates.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Libro/create.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Función insertLibro.
     * Se manda llamar cuando se da click en el botón de Guardar, el cual desde
     * la vista le manda al controlador la palabra "Insert", posteriormente es
     * reconocida en el GET y se llama a esta función.
     * La vista también almacena los valores del libro, los cuales en
     * esta función se reciben por medio de erquest.getParameter para finalmente
     * realizar el insert en la base de datos.
     * @param request servlet request
     * @param response servlet response
     * @throws SQLException
     * @throws IOException 
     */
    private void insertLibro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String titulo = request.getParameter("titulo");
        //int año = Integer.parseInt(request.getParameter("aaa"));
        int año = 2000;
        String autor = request.getParameter("autor");
        String sinopsis = request.getParameter("sinopsis");
        String editorial = request.getParameter("editorial");
        con.insertarLibro(new Libro(titulo,año,autor,sinopsis,editorial));
        response.sendRedirect("Libros");
    }

    /**
     * Función Delete Esta función se manda llamar desde el GET, cuando
     * se da click en el botón de 'eliminar' en alguna de los libros.
     * Cuando se da click en el botón de 'eliminar' la vista le manda al
     * controlador la palabra "Delete" más el ID del libro, posteriormente
     * estos datos son reconocidos en el GET y se llama a esta función, la cual
     * manda llamar a la función que ejecuta la query de eliminar una dirección.
     * @param request servlet request
     * @param response servlet response
     * @param id ID del libro a eliminar.
     * @throws IOException
     * @throws SQLException
     */
    private void Delete(HttpServletRequest request, HttpServletResponse response, int id) 
            throws IOException, SQLException{
        con.eliminarLibro(id);
        response.sendRedirect("Libros");

    }

    /**
     * Función showEditForm. Se manda llamar cuando se da click en el botón de
     * 'Editar' de un libro, el cual desde la vista index.jsp le manda al
     * controlador la palabra 'Edit' más el id del libro a editar, estos
     * parámetros son reconocidos en el GET y de ahí se llama a esta función, la
     * cual se encarga de cargar la vista del formulario para editar el
     * libro, mostrando los datos preecargados que tiene el libro a
     * editar, esto gracias a que al atributo 'libro' de la vista se le
     * asigna la dirección correspondiente al id recibido por medio de una
     * consulta al controlador de la bd.
     *
     * @param request servlet request
     * @param response servlet response
     * @param id id del libro
     * @throws IOException
     * @throws SQLException
     * @throws ServletException
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response, int id)
            throws IOException, SQLException, ServletException {
        Libro libro = con.obtenerLibro(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Libro/create.jsp");
        request.setAttribute("libro", libro);
        dispatcher.forward(request, response);
        
    }

     /**
     * Función Update.
     * Se encarga de modificar los datos de un libro en la base de datos.
     * Los nuevos valores los recibe por parte del jsp de create y por medio de
     * parámetros.
     * @param request servlet request
     * @param response servlet response
     * @param id id del libro.
     * @throws IOException
     * @throws SQLException
     * @throws ServletException 
     */
    private void update(HttpServletRequest request, HttpServletResponse response, int id)
            throws IOException, SQLException, ServletException {
        String titulo = request.getParameter("titulo");
        int año = Integer.parseInt(request.getParameter("aaa"));
        String autor = request.getParameter("autor");
        String sinopsis = request.getParameter("sinopsis");
        String editorial = request.getParameter("editorial");
        con.actualizarLibro(new Libro(id, titulo, año, autor, sinopsis, editorial));
        response.sendRedirect("Libros");
    }

    /**
     * Función ShowlIBROS.
     * Se encarga de realizar la consulta de obtener todos los registros de 
     * los libros en la base de datos y de mandárselas a la vista por
     * medio de la función request.setAttribute.
         * @param request servlet request
     * @param response servlet response
     * @throws SQLException error en la consulta.
     * @throws IOException error IO.
     * @throws ServletException error en el servlet.
     */
    private void ShowLibros(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        List<Libro> libros = con.obtenerLibros();
        request.setAttribute("listLibros", libros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Libro/index.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Función Sell
     * Esta función se manda llamar cuando el usuario da click en vender en 
     * alguno de los libros, y lo que ésta función hace es cargar el jsp
     * de sell, obtener los datos del libro correspondiente a vender, y 
     * mandarlos al jsp y mostrarlos por medio de parámetros.
     * @param request
     * @param response
     * @param id id del libro.
     * @throws IOException
     * @throws ServletException 
     */
    private void Sell(HttpServletRequest request, HttpServletResponse response, int id) throws IOException, ServletException {
        Libro libro = con.obtenerLibro(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Libro/sell.jsp");
        request.setAttribute("libro", libro);
        dispatcher.forward(request, response);

    }

    /**
     * Función Sold
     * Se manda llamar cuando el usuario vende un libro.
     * Lo que hace es obtener el precio por medio de parámetro y el id del 
     * usuario por medio de sessión.getAttribute. Posteriormente realiza un 
     * insert en la tabla venta_libro.
     * @param request
     * @param response
     * @param libroID id del libro
     * @throws IOException 
     */
    private void Sold(HttpServletRequest request, HttpServletResponse response, int libroID) {
        int precio = Integer.parseInt(request.getParameter("precio"));
        HttpSession session = (HttpSession) request.getSession();
        int userid = Integer.parseInt(session.getAttribute("id").toString());
        con.insertarVentaLibro(new VentaLibro(precio, userid, libroID));
        try {
            response.sendRedirect("Libros");
        } catch (IOException ex) {
            Logger.getLogger(Peliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Función misLibrosVendidos
     * Esta función se manda llamar cuando el usuario da click en el botón de
     * 'mis libros vendidos', en este se muestra el id de venta, el nombre
     * del libro y el precio del libro de cada uno de los libros
     * vendidos por el usuario logueado.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void misLibrosVendidos(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        
        HttpSession session = (HttpSession) request.getSession();
        int userid = Integer.parseInt(session.getAttribute("id").toString());
        List<VentaLibro> libros = con.obtenerVentaLibroUser(userid);
        
        request.setAttribute("listLibros", libros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Libro/misLibros.jsp");
        dispatcher.forward(request, response);
    }
}
