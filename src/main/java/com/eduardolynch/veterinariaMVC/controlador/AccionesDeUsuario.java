package com.eduardolynch.veterinariaMVC.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eduardolynch.veterinariaMVC.modelo.UsuarioJpaController;
import com.eduardolynch.veterinariaMVC.entidad.Usuario;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eduardo Lynch Araya
 */
public class AccionesDeUsuario extends HttpServlet {

    @PersistenceContext(unitName = "VeterinariaPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String accion = request.getParameter("accion");
        //Variable de Session
        HttpSession respuesta = request.getSession(true);
        out.println("<h1>?accion=" + accion.toString() + "</h1>");
        try {
            UsuarioJpaController acciones = new UsuarioJpaController(utx);
            //Crear Usuario
            if (accion.equalsIgnoreCase("crear")) {
                String nomUsuario = request.getParameter("nomUsuario");
                String apeUsuario = request.getParameter("apeUsuario");
                String emailUsuario = request.getParameter("emailUsuario");
                String roleUsuario = request.getParameter("roleUsuario");
                String passUsuario = request.getParameter("passUsuario");
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombre(nomUsuario);
                nuevoUsuario.setApellido(apeUsuario);
                nuevoUsuario.setEmail(emailUsuario);
                nuevoUsuario.setRol(roleUsuario);
                nuevoUsuario.setPassword(passUsuario);

                if (acciones.findUsuarioByEmail(emailUsuario) == null) {
                    acciones.create(nuevoUsuario);
                    //Ir al Indice
                    response.sendRedirect(request.getContextPath() + "/");
                } else {
                    out.println("<h2>Ya existe un Usuario Asociado al siguiente E-Mail:</h2>");
                    out.println(emailUsuario);
                    out.println("<br /><br />Seras redireccionado al Inicio en 5 Segundos...");
                    response.setHeader("Refresh", "5; URL=" + request.getContextPath());
                }
                //Ir a la pagina de Registro
            } else if (accion.equalsIgnoreCase("registrar")) {
                RequestDispatcher rd = request.getRequestDispatcher("usuario/Registro.jsp");
                rd.forward(request, response);
                //Iniciar Sesion
            } else if (accion.equalsIgnoreCase("login")) {
                if (respuesta.isNew()) {
                    respuesta.invalidate();
                }

                String emailUsuario = request.getParameter("emailUsuario");
                String passUsuario = request.getParameter("passUsuario");
                //Comprobamos Email y Contraseña
                if (acciones.isEmailAndPasswordCorrect(emailUsuario, passUsuario)) {
                    Usuario logged = acciones.findUsuarioByEmail(emailUsuario);
                    respuesta.setAttribute("Id", logged.getIdUsuario());
                    respuesta.setAttribute("Nombre", logged.getNombre());
                    respuesta.setAttribute("Apellido", logged.getApellido());
                    respuesta.setAttribute("Email", logged.getEmail());
                    respuesta.setAttribute("Rol", logged.getRol());
                    if (logged.getRol().equalsIgnoreCase("MEDICO")) {
                        RequestDispatcher rd = request.getRequestDispatcher("AccionesDeFicha?accion=listarfichas");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("AccionesDeMascota?accion=listar");
                        rd.forward(request, response);
                    }
                } else {
                    out.println("<br /><h2>Usuario o Contraseña Incorrecto</h2>");
                    out.println("<br />Seras redireccionado al Inicio en 5 Segundos...");
                    response.setHeader("Refresh", "5; URL=" + request.getContextPath());
                }
            } else {
                out.println("<h1>Error:  No he recibido ningun parametro valido</h1>");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            out.println("<h1>Error:  " + ex.getLocalizedMessage() + " - " + ex.getMessage() + "</h1>");
        }
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
        processRequest(request, response);
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
