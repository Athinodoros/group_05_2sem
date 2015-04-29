/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer1.presentation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import layer2.domain.Controller;
import layer2.domain.bean.Partner;
import layer2.domain.bean.Project;
import layer2.domain.bean.UserInfo;
import layer2.domain.interfaces.NamingConv;
import layer3.dataSource.utility.Convert;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
            ctrl = new Controller();
            session.setAttribute("Controller", ctrl);
        }

        String command = (String) request.getParameter("command");
        switch (command) {
            case "log-in":
                dummyLogIn(request, response);
                viewAllProjects(request, response);
                break;
                        
            case "createCompany":
                createCompany(request, response);
                break;

            case "createProject":
                createProject(request, response);
                break;

            case "reloadMain":
                RequestDispatcher dispatcher;
                String main = (String) request.getParameter("mainArea");
                switch (main) {
                    case NamingConv.PROJECTLIST:
                        request.setAttribute("mainArea", NamingConv.PROJECTLIST);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.SET_BUDGET:
                        request.setAttribute("mainArea", NamingConv.SET_BUDGET);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.VIEW_BUDGET:
                        request.setAttribute("mainArea", NamingConv.VIEW_BUDGET);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.CREATECOMPANY:
                        request.setAttribute("mainArea", NamingConv.CREATECOMPANY);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.USERFORM:
                        request.setAttribute("mainArea", NamingConv.USERFORM);
                        request.setAttribute("partnerList", ctrl.getAllPartners());
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.CREATEPROJECT:
                        request.setAttribute("mainArea", NamingConv.CREATEPROJECT);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.PROJECT_OVERVIEW:
                        viewAllProjects(request, response);
                        break;
                }
                break;
        }
    }
    
    private void dummyLogIn(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute("Controller");
        String input = request.getParameter("username");
            if (input.equals("admin")) {
                session.setAttribute("user", ctrl.getAdmin());
            } else {
                session.setAttribute("user", ctrl.getReseller());
            }
    }

    private void createProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute("Controller");
        UserInfo currentUser = (UserInfo) session.getAttribute("user");
        Project project = (Project) session.getAttribute("newProject");
        project.setPartner(currentUser.getCompany());
        project.setStage(NamingConv.PRE_APPROVED);
        String sdate = (String) session.getAttribute("sdate");
        String fdate = (String) session.getAttribute("fdate");
        project.setSdate(Convert.string2date(sdate));
        project.setFdate(Convert.string2date(fdate));
        boolean status = ctrl.createProject(project);
        if (status) {
            request.setAttribute("mainArea", NamingConv.SUCCESS);
        }
        else{
            request.setAttribute("mainArea", NamingConv.FAIL);
        }
        request.setAttribute("type", NamingConv.PROJECT);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);

    }

    private void viewAllProjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mainArea", NamingConv.PROJECT_OVERVIEW);
        
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute("Controller");
        UserInfo currentUser = (UserInfo) session.getAttribute("user");
        
        ArrayList<Project> allProjects = (ArrayList<Project>) ctrl.getAllProjects();
        
        if (currentUser.getUrole().equals(NamingConv.ADMIN)) {
            request.setAttribute("projects", allProjects);
        } else if (currentUser.getUrole().equals(NamingConv.PARTNER)) {
            ArrayList<Project> onlyPartnerProjects = new ArrayList<Project>();
            for (Project project : allProjects) {
                if (project.getPartner().getCompanyName().equals(currentUser.getCompany().getCompanyName())) {
                    onlyPartnerProjects.add(project);
                }
            }
            request.setAttribute("projects", onlyPartnerProjects);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void createCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute("Controller");
        Partner partner = (Partner) session.getAttribute("newCompany");       
        ctrl.createPartner(partner);
        request.setAttribute("mainArea", NamingConv.CREATECOMPANY);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
    }

    
    private void upload(HttpServletRequest request, HttpServletResponse response, Controller con) throws ServletException, IOException {
        String fileDirec = "../" + con.getFileDirec(Integer.parseInt(request.getParameter("projectid")));

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        item.write(new File(fileDirec + File.separator + name));
                    }
                }

                //File uploaded successfully
                request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }

        } else {
            request.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }

        //request.getRequestDispatcher("/result.jsp").forward(request, response);
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
