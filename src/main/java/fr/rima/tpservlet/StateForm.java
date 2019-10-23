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
@WebServlet(name = "stateForm", urlPatterns = {"/stateForm"})
public class StateForm extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StateForm</title>");            
            out.println("</head>");
            out.println("<body>");
            
            try {   
                
 
                DAO2 dao = new fr.rima.tpservlet.DAO2(DataSourceFactory.getDataSource());
                List<String> state = dao.States() ;
                
               out.print("<form method='POST' action=clientInState><select name='state'>");
               for (int i=0; i<state.size();i++){
                   out.printf(" <option value='%s'>%s</option>", state.get(i), state.get(i));
               }
                out.print("</select><button type='submit'>Recherche</button></form>");
  
                
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
