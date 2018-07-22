/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduardolynch.veterinariaMVC.controlador;

import com.eduardolynch.veterinariaMVC.entidad.Ficha;
import com.eduardolynch.veterinariaMVC.modelo.FichaJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eduardo Lynch Araya
 */
public class AccionesDeFicha extends HttpServlet {

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
        out.println("<h1>?accion=" + accion + "</h1>");
        int idUsuario = Integer.parseInt(respuesta.getAttribute("Id").toString());
        try {
            FichaJpaController acciones = new FichaJpaController(utx);
            //Lista Ficha
            if (accion.equalsIgnoreCase("listarfichas")) {
                List<Ficha> listaFichas = (List<Ficha>) acciones.findFichaByIdUsuario(idUsuario);
                request.setAttribute("listaFichas", listaFichas);
                RequestDispatcher rd = request.getRequestDispatcher("fichamedica/ListaFichas.jsp");
                rd.forward(request, response);
            } else if (accion.equalsIgnoreCase("editar")) {
                int idFicha = Integer.parseInt(request.getParameter("idFicha"));
                int idMascota = Integer.parseInt(request.getParameter("idMascota"));
                Ficha fichaModif = new Ficha();
                fichaModif.setIdFicha(idFicha);
                fichaModif.setIdUsuario(idUsuario);
                fichaModif.setIdMascota(idMascota);
                fichaModif.setMotivo(request.getParameter("motivo"));
                fichaModif.setTitulo(request.getParameter("titulo"));
                acciones.edit(fichaModif);
                //recargar la lista!
                List<Ficha> listaFichas = (List<Ficha>) acciones.findFichaByIdUsuario(idUsuario);
                request.setAttribute("listaFichas", listaFichas);
                RequestDispatcher rd = request.getRequestDispatcher("fichamedica/ListaFichas.jsp");
                rd.forward(request, response);
            } else if (accion.equalsIgnoreCase("borrar")) {
                String idFicha = request.getParameter("idFicha");
                acciones.destroy(Integer.parseInt(idFicha));
                //recargar la lista!
                List<Ficha> listaFichas = (List<Ficha>) acciones.findFichaByIdUsuario(idUsuario);
                request.setAttribute("listaFichas", listaFichas);
                RequestDispatcher rd = request.getRequestDispatcher("fichamedica/ListaFichas.jsp");
                rd.forward(request, response);
            } else if (accion.equalsIgnoreCase("crear")) {
                int idMascota = Integer.parseInt(request.getParameter("idMascota"));
                String motivo = request.getParameter("motivo");
                String titulo = request.getParameter("titulo");
                Ficha fichaNueva = new Ficha();
                fichaNueva.setIdUsuario(idUsuario);
                fichaNueva.setTitulo(titulo);
                fichaNueva.setIdMascota(idMascota);
                fichaNueva.setMotivo(motivo);
                acciones.create(fichaNueva);
                //recargar la lista!
                List<Ficha> listaFichas = (List<Ficha>) acciones.findFichaByIdUsuario(idUsuario);
                request.setAttribute("listaFichas", listaFichas);
                RequestDispatcher rd = request.getRequestDispatcher("fichamedica/ListaFichas.jsp");
                rd.forward(request, response);
            } else if (accion.equalsIgnoreCase("cargar")) {
                int idFicha = Integer.parseInt(request.getParameter("idFicha"));
                Ficha fichaaEncontrado = (Ficha) acciones.findFicha(idFicha);
                request.setAttribute("ficha", fichaaEncontrado);
                RequestDispatcher rd = request.getRequestDispatcher("fichamedica/ModificarFichas.jsp");
                rd.forward(request, response);
            } else if (accion.equalsIgnoreCase("agregar")) {
                RequestDispatcher rd = request.getRequestDispatcher("fichamedica/AgregarFichas.jsp");
                rd.forward(request, response);
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
