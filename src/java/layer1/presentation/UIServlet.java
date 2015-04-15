/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer1.presentation;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import layer2.domain.Controller;
import layer2.domain.bean.User;
import layer2.domain.interfaces.NamingConv;
import layer2.domain.bean.Company;

//comment
/**
 *
 * @author Bancho
 */
@WebServlet(name = "UIServlet", urlPatterns = {"/UIServlet"})
public class UIServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute("Controller");
        if (ctrl == null) {
            ctrl = Controller.getInstance();
            session.setAttribute("Controller", ctrl);
        }

        String command = (String) request.getParameter("command");
        String Main = (String) request.getParameter("mainArea");
        RequestDispatcher dispatcher;
        switch (command) {
            case "log-in":
                //dummy code starts here
                Company resellComp = new Company("Reseller", 1);
                Company dell = new Company("Dell", 1000000);
                User user1 = new User(1, "Nos", "1234", "nos@paok.gr", "Greece", NamingConv.RESELLER, resellComp);
                User user2 = new User(1, "Bo", "1234", "bo@cph.dk", "Denmark", NamingConv.ADMIN, dell);
                String input = request.getParameter("email");
                if (input.equals("admin")) {
                    session.setAttribute("user", user2);
                }else{
                    session.setAttribute("user", user1);
                }
                dispatcher = request.getRequestDispatcher("dashboard.jsp");
                dispatcher.forward(request, response);
                //dummy code ends here
                
                //validate credentials
                break;
                
            case "initialForm":
                dispatcher = request.getRequestDispatcher("initialForm.jsp");
                dispatcher.forward(request, response);
                break;
                
            case "reloadMain":
                //dummy code start
                switch (Main) {
                    case NamingConv.PROJECTLIST:
                        request.setAttribute("mainArea", NamingConv.PROJECTLIST);
                        dispatcher = request.getRequestDispatcher("dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.BUDGET:
                        request.setAttribute("mainArea", NamingConv.BUDGET);
                        dispatcher = request.getRequestDispatcher("dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                        
                }
                dispatcher = request.getRequestDispatcher("initialForm.jsp");
                dispatcher.forward(request, response);
                //dummy code end
                break;
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
