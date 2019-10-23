/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rima.tpservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author marie
 */
        @WebServlet(name = "clientInStateForm", urlPatterns = {"/clientInStateForm"})

public class clientInStateForm extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet clientInStateForm</title>");            
            out.println("</head>");
            out.println("<body>");
            
            try {   
                
 
                DAO2 dao = new fr.rima.tpservlet.DAO2(DataSourceFactory.getDataSource());
                List<String> state = dao.States() ;
                
               out.print("<form method='POST' ><select name='state'>");
               for (int i=0; i<state.size();i++){
                   out.printf(" <option value='%s'>%s</option>", state.get(i), state.get(i));
               }
                out.print("</select><button type='submit'>Recherche</button></form>");
  
                String val = request.getParameter("state");
                if (val != null) {
                    List<CustomerEntity> customers = dao.customersInState(val);
                    
                    out.print("<table border=\"1\">  <tr>  <th>ID</th><th>NAME</th><th>ADDRESS</th> </tr>");
                    for (int x=0; x<customers.size();x++){
                        CustomerEntity c = customers.get(x);
                        out.printf("<tr><td> %d </td> <td> %s </td> <td> %s</td>",c.getCustomerId(),c.getName(),c.getAddressLine1());
                    }

                    out.print("</table");

                }
                
            } catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
            out.printf("<hr><a href='%s'>Retour au menu</a>", request.getContextPath());
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
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
