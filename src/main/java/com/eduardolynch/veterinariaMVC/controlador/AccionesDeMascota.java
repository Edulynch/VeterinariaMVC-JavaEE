package com.eduardolynch.veterinariaMVC.controlador;

import com.eduardolynch.veterinariaMVC.entidad.Mascota;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eduardolynch.veterinariaMVC.modelo.MascotaJpaController;
import com.eduardolynch.veterinariaMVC.modelo.UsuarioJpaController;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eduardo Lynch Araya
 */
public class AccionesDeMascota extends HttpServlet {

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
        HttpSession respuesta = request.getSession(true);
        String accion = request.getParameter("accion");
        int idUsuarioSession = Integer.parseInt(respuesta.getAttribute("Id").toString());

        try {
            MascotaJpaController acciones = new MascotaJpaController(utx);
            if (accion.equalsIgnoreCase("listar")) {
                List<Mascota> lista = (List<Mascota>) acciones.findMascotaByIdUsuario(idUsuarioSession);
                request.setAttribute("listaMascotas", lista);
                RequestDispatcher rd = request.getRequestDispatcher("mascota/ListaMascotas.jsp");
                rd.forward(request, response);
            } else if (accion.equalsIgnoreCase("editar")) {
                String idMascota = request.getParameter("idMascota");
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                String nomMascota = request.getParameter("nomMascota");
                String raza = request.getParameter("razaMascota");
                Mascota mascotaModif = new Mascota();
                mascotaModif.setIdUsuario(idUsuario);
                mascotaModif.setIdMascota(Integer.parseInt(idMascota));
                mascotaModif.setNombre(nomMascota);
                mascotaModif.setRaza(raza);
                acciones.edit(mascotaModif);
                //recargar la lista!
                List<Mascota> lista = (List<Mascota>) acciones.findMascotaByIdUsuario(idUsuarioSession);
                request.setAttribute("listaMascotas", lista);
                RequestDispatcher rd = request.getRequestDispatcher("mascota/ListaMascotas.jsp");
                rd.forward(request, response);
            } else if (accion.equalsIgnoreCase("borrar")) {
                String idMascota = request.getParameter("idMascota");
                acciones.destroy(Integer.parseInt(idMascota));
                //recargar la lista!
                List<Mascota> lista = (List<Mascota>) acciones.findMascotaByIdUsuario(idUsuarioSession);
                request.setAttribute("listaMascotas", lista);
                RequestDispatcher rd = request.getRequestDispatcher("mascota/ListaMascotas.jsp");
                rd.forward(request, response);
            } else if (accion.equalsIgnoreCase("crear")) {
                int idUsuario = idUsuarioSession;
                String nomMascota = request.getParameter("nomMascota");
                String raza = request.getParameter("razaMascota");
                Mascota mascotaNueva = new Mascota();
                mascotaNueva.setIdUsuario(idUsuario);
                mascotaNueva.setNombre(nomMascota);
                mascotaNueva.setRaza(raza);
                acciones.create(mascotaNueva);
                //recargar la lista!
                List<Mascota> lista = (List<Mascota>) acciones.findMascotaByIdUsuario(idUsuarioSession);
                request.setAttribute("listaMascotas", lista);
                RequestDispatcher rd = request.getRequestDispatcher("mascota/ListaMascotas.jsp");
                rd.forward(request, response);
            } else if (accion.equalsIgnoreCase("cargar")) {
                String idMascota = request.getParameter("idMascota");
                Mascota mascotaEncontrado = (Mascota) acciones.findMascota(Integer.parseInt(idMascota));
                request.setAttribute("mascota", mascotaEncontrado);
                RequestDispatcher rd = request.getRequestDispatcher("mascota/ModificarMascotas.jsp");
                rd.forward(request, response);
            } else if (accion.equalsIgnoreCase("agregar")) {
                RequestDispatcher rd = request.getRequestDispatcher("mascota/AgregarMascotas.jsp");
                rd.forward(request, response);
            } else {
                out.println("<h1>Error:  No he recibido ningun parametro valido</h1>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            out.println("<h1>Error:  " + ex.getLocalizedMessage() + " - " + ex.getMessage() + "</h1>");

        }
    }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    } // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

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
