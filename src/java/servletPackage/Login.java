/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ricar
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    DBController dbc;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public Login() {
        dbc = new DBController();
    }

    /**
     * Función processRequest Esta función se encarga del funcionamiento del
     * inicio de sesión. Cuando el usuario presiona el botón de iniciar sesión,
     * esta función recibe las credenciales por medio de un parámetro,
     * posetriormente se llama a la función selectUser del controlador de la
     * base de datos, para comprobar si las credenciales ingresadas corresponden
     * o no a las de un registro. si son correctas, al atributo "logged" se le
     * almacena el nombre del usuario, y si es incorrecto, al atributo
     * "failedLoggin" se le almacena un true. De esta manera el jsp imprime html
     * distinto dependiendo del resultado.s
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String link = request.getParameter("link");
        RequestDispatcher dispatcher;
        if (link.equals("Login")) {
            String user = request.getParameter("User");
            String password = request.getParameter("Password");
            if (dbc.selectUser(user, password)) {
                //request.setAttribute("user", user);
                RequestDispatcher rd = request.getRequestDispatcher("Main");
                rd.forward(request, response);
                //response.sendRedirect("Main");
                //dispatcher = request.getRequestDispatcher("/Main/index.jsp");
            } else {
                request.setAttribute("failedLogging", true);
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);

            }
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


}
