package servletPackage;

import Models.VentaVideojuego;
import Models.Videojuego;
import java.io.IOException;
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

/**
 *
 * @author Ricardo-Angel-Erick Clase Videojuego Esta clase es el servlet
 * utilizado para el manejo del crud de videojuegos y del sistema de venta de
 * videojuegos. En el doGet de este servlet, se verifica que haya un usuario
 * logueado, de lo contrario lo redirecciona a el login para que inicie sesión.
 * Este servlet conecta con cuatro diferentes JSP utilizados para el sistema,
 * uno para mostrar un listado de los videojuegos, otro para dar de alta uno
 * nuevo, otro para venderlo y otro para mostrar un listado de los videojuegos
 * vendidos. La manera en la que se realiza la comunicación de información es
 * por medio de parámetros utilizados en inputs y botones en forms.
 */
@WebServlet(name = "Videojuegos", urlPatterns = {"/Videojuegos"})
public class Videojuegos extends HttpServlet {

    DBController con;

    public Videojuegos() {
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
                ShowVideojuegos(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(Videojuegos.class.getName()).log(Level.SEVERE, null, ex);
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
                ShowVideojuegos(request, response);
            } else {
                String[] splitedLink = link.split("/");
                switch (splitedLink[0]) {
                    case "Agregar":
                        showCreate(request, response);
                        break;
                    case "Insert":
                        insertVideojuego(request, response);
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
                    case "Mis videojuegos vendidos":
                        misJueguitosVendidos(request, response);
                        break;
                    default:
                        ShowVideojuegos(request, response);
                        break;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Videojuegos.class.getName()).log(Level.SEVERE, null, ex);
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
    }// </editor-fold>// </editor-fold>

    /**
     * Función showCreate. Se manda llamar cuando se da click en el botón de
     * 'Agregar', el cual desde la vista index.jsp le manda al controlador la
     * palabra 'Agregar', la cual es reconocida en el GET y se llama a esta
     * función, la cual se encarga de cargar la vista del formulario para
     * agregar un videojuego.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    private void showCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Videojuego/create.jsp");
        dispatcher.forward(request, response);
    }
 
    /**
     * Función insertVideojuego.
     * Se manda llamar cuando se da click en el botón de Guardar, el cual desde
     * la vista le manda al controlador la palabra "Insert", posteriormente es
     * reconocida en el GET y se llama a esta función.
     * La vista también almacena los valores del videojuego, los cuales en
     * esta función se reciben por medio de request.getParameter para finalmente
     * realizar el insert en la base de datos.
     * @param request servlet request
     * @param response servlet response
     * @throws SQLException
     * @throws IOException 
     */
    private void insertVideojuego(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String titulo = request.getParameter("titulo");
        int año = Integer.parseInt(request.getParameter("ano"));
        String desarrollador = request.getParameter("desarrollador");
        String distribuidora = request.getParameter("distribuidora");
        String clasificacion = request.getParameter("clasificacion");
        con.insertarVideojuego(new Videojuego(titulo,año,desarrollador,distribuidora,clasificacion));
        response.sendRedirect("Videojuegos");
    }

    /**
     * Función Delete Esta función se manda llamar desde el GET, cuando
     * se da click en el botón de 'eliminar' en alguna de los videojuegos.
     * Cuando se da click en el botón de 'eliminar' la vista le manda al
     * controlador la palabra "Delete" más el ID de la dirección, posteriormente
     * estos datos son reconocidos en el GET y se llama a esta función, la cual
     * manda llamar a la función que ejecuta la query de eliminar un videojuego.
     * @param request servlet request
     * @param response servlet response
     * @param id ID de la dirección a eliminar.
     * @throws IOException
     * @throws SQLException
     */
    private void Delete(HttpServletRequest request, HttpServletResponse response, int id) 
            throws IOException, SQLException{
        con.eliminarVideojuego(id);
        response.sendRedirect("Videojuegos");

    }

    /**
     * Función showEditForm. Se manda llamar cuando se da click en el botón de
     * 'Editar' de una direccion, el cual desde la vista index.jsp le manda al
     * controlador la palabra 'Edit' más el id del videojuego a editar, estos
     * parámetros son reconocidos en el GET y de ahí se llama a esta función, la
     * cual se encarga de cargar la vista del formulario para editar la
     * dirección, mostrando los datos preecargados que tiene el videojuego a
     * editar, esto gracias a que al atributo 'videojuego' de la vista se le
     * asigna la videojuego correspondiente al id recibido por medio de una
     * consulta al controlador de la bd.
     *
     * @param request servlet request
     * @param response servlet response
     * @param id id del videojuego.
     * @throws IOException
     * @throws SQLException
     * @throws ServletException
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response, int id)
            throws IOException, SQLException, ServletException {
        Videojuego videojuego = con.obtenerVideojuego(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Videojuego/create.jsp");
        request.setAttribute("videojuego", videojuego);
        dispatcher.forward(request, response);
        
    }

    /**
     * Función Update.
     * Se encarga de modificar los datos de un videojuego en la base de datos.
     * Los nuevos valores los recibe por parte del jsp de create y por medio de
     * parámetros.
     * @param request servlet request
     * @param response servlet response
     * @param id id del videojuego.
     * @throws IOException
     * @throws SQLException
     * @throws ServletException 
     */
    private void update(HttpServletRequest request, HttpServletResponse response, int id)
            throws IOException, SQLException, ServletException {
       String titulo = request.getParameter("titulo");
        int año = Integer.parseInt(request.getParameter("ano"));
        String desarrollador = request.getParameter("desarrollador");
        String distribuidora = request.getParameter("distribuidora");
        String clasificacion = request.getParameter("clasificacion");
        con.actualizarVideojuego(new Videojuego(id, titulo, año, desarrollador, distribuidora, clasificacion));
        response.sendRedirect("Videojuegos");
    }

    /**
     * Función ShowVideojuegos.
     * Se encarga de realizar la consulta de obtener todos los registros de 
     * los videojuegos en la base de datos y de mandárselas a la vista por
     * medio de la función request.setAttribute.
     * @param request servlet request
     * @param response servlet response
     * @throws SQLException error en la consulta.
     * @throws IOException error IO.
     * @throws ServletException error en el servlet.
     */
    private void ShowVideojuegos(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        List<Videojuego> videojuegos = con.obtenerVideojuegos();
        request.setAttribute("listVideojuegos", videojuegos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Videojuego/index.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Función Sell
     * Esta función se manda llamar cuando el usuario da click en vender en 
     * algun de los videojuegos, y lo que ésta función hace es cargar el jsp
     * de sell, obtener los datos del videojuego correspondiente a vender, y 
     * mandarlos al jsp y mostrarlos por medio de parámetros.
     * @param request
     * @param response
     * @param id id del videojuego.
     * @throws IOException
     * @throws ServletException 
     */
    private void Sell(HttpServletRequest request, HttpServletResponse response, int id) throws IOException, ServletException {
        Videojuego videojuego = con.obtenerVideojuego(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Videojuego/sell.jsp");
        request.setAttribute("videojuego", videojuego);
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
     * @param videojuegoID id del videojuego
     * @throws IOException 
     */
    private void Sold(HttpServletRequest request, HttpServletResponse response, int videojuegoID) throws IOException {
        int precio = Integer.parseInt(request.getParameter("precio"));
        HttpSession session = (HttpSession) request.getSession();
        int userid = Integer.parseInt(session.getAttribute("id").toString());
        con.insertarVentaVideoJuego(new VentaVideojuego(precio, userid, videojuegoID));
            response.sendRedirect("Videojuegos");
           
    }
    
    /**
     * Función MisJueguitosVendidos
     * Esta función se manda llamar cuando el usuario da click en el botón de
     * 'mis videojuegos vendidos', en este se muestra el id de venta, el nombre
     * del videojuego y el precio del videojuego de cada uno de los videojuegos
     * vendidos por el usuario logueado.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void misJueguitosVendidos(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        
        HttpSession seVentaPeliculassion = (HttpSession) request.getSession();
        int userid = Integer.parseInt(seVentaPeliculassion.getAttribute("id").toString());
        
        List<VentaVideojuego> videojuegos = con.obtenerVentaVideoJuegoUser(userid);
        
        request.setAttribute("listVideojuegos", videojuegos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Videojuego/misvideojuegos.jsp");
        dispatcher.forward(request, response);
    }
}
