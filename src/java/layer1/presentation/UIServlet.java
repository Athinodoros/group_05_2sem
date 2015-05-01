/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer1.presentation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import layer2.domain.Controller;
import layer2.domain.bean.POE;
import layer2.domain.bean.Partner;
import layer2.domain.bean.Project;
import layer2.domain.bean.UserAuthentication;
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
        RequestDispatcher  dispatcher ;
         
        if (command == null) {
            command = (String) session.getAttribute("command");
        }
        switch (command) {
            case "log-in":
                dummyLogIn(request, response);
                viewProjects(request, response);
                break;
                
            case NamingConv.UPLOAD:
                upload(request, response, ctrl);
                break;

            case NamingConv.CREATEUSER:
                createUser(request, response);
                break;
                
            case "createCompany":
                createCompany(request, response);
                break;

            case "createProject":
                createProject(request, response);
                break;

            case "reloadMain":
                String mainArea = (String) request.getParameter("mainArea");
                if (mainArea == null) {
                    mainArea = (String) session.getAttribute("mainArea");
                 }
                switch (mainArea) {
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
                    case NamingConv.SEE:
                        request.setAttribute("mainArea", NamingConv.SEE);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.PROJECT_OVERVIEW:
                    case NamingConv.PENDING_PROJECTS:
                    case NamingConv.APPROVED_PROJECTS:
                        viewProjects(request, response);
                        break;
                    default:
                        request.setAttribute("mainArea", NamingConv.SEE);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                        
                }
                break;
                default:
                    request.setAttribute("mainArea", NamingConv.SEE);
                    dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                    dispatcher.forward(request, response);
                    break;
        }
    }

    private void dummyLogIn(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute("Controller");
        String input = request.getParameter("username");
        if (input.equals("admin")) {
            session.setAttribute("user", ctrl.getAdmin());
        } else if (input.equalsIgnoreCase("bancho")) {
            session.setAttribute("user", ctrl.getBancho());
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
        project.setStage(NamingConv.PENDING);
        String sdate = (String) session.getAttribute("sdate");
        String fdate = (String) session.getAttribute("fdate");
        project.setSdate(Convert.string2date(sdate));
        project.setFdate(Convert.string2date(fdate));
        boolean status = ctrl.createProject(project);
        if (status) {
            request.setAttribute("mainArea", NamingConv.SUCCESS);
        } else {
            request.setAttribute("mainArea", NamingConv.FAIL);
        }
        request.setAttribute("type", NamingConv.PROJECT);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void viewProjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mainArea", NamingConv.PROJECT_OVERVIEW);
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute("Controller");
        UserInfo currentUser = (UserInfo) session.getAttribute("user");
        ArrayList<Project> allProjects = (ArrayList<Project>) ctrl.getAllProjects();

        if (currentUser.getUrole().equals(NamingConv.ADMIN)) {
            String requsted = request.getParameter("mainArea");

            switch (requsted) {
                case NamingConv.PROJECT_OVERVIEW:
                    request.setAttribute("projects", allProjects);
                    break;
                case NamingConv.PENDING_PROJECTS:
                    ArrayList<Project> pendingProjects = new ArrayList<Project>();
                    for (Project project : allProjects) {
                        if (project.getStage().equals(NamingConv.PENDING)) {
                            pendingProjects.add(project);
                        }
                    }
                    request.setAttribute("projects", pendingProjects);
                    break;
                case NamingConv.APPROVED_PROJECTS:
                    ArrayList<Project> approvedProjects = new ArrayList<Project>();
                    for (Project project : allProjects) {
                        if (project.getStage().equals(NamingConv.PENDING) == false) {
                            approvedProjects.add(project);
                        }
                    }
                    request.setAttribute("projects", approvedProjects);
                    break;
            }

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
        boolean status = ctrl.createPartner(partner);
        if (status) {
            request.setAttribute("mainArea", NamingConv.SUCCESS);
        } else {
            request.setAttribute("mainArea", NamingConv.FAIL);
        }
        request.setAttribute("type", "company");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
    }
    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute("Controller");
        UserAuthentication newUserAth = (UserAuthentication) session.getAttribute("newUserAth");
        UserInfo newUserInfo = (UserInfo)session.getAttribute("newUserInfo");
        newUserAth.setUserInfo(newUserInfo);
        boolean status = ctrl.createUserInfo(newUserInfo);
        //need the userID to continue ferther
        boolean status1 = ctrl.createUserAth(newUserAth);
        if (status&&status1) {
            request.setAttribute("mainArea", NamingConv.SUCCESS);
        } else {
            request.setAttribute("mainArea", NamingConv.FAIL);
        }
        request.setAttribute("type", "User");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void upload(HttpServletRequest request, HttpServletResponse response, Controller con) throws ServletException, IOException {
        File file;
        POE poe = new POE();
        poe.setProject(con.getProjects(1));
        ServletContext context = this.getServletContext();
        String filePath = context.getInitParameter("file-upload");
        String contentType = request.getContentType();
        if (contentType.indexOf("multipart/form-data") >= 0) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(Long.MAX_VALUE);
            try {
                List files = upload.parseRequest(request);
                Iterator it = files.iterator();
                while (it.hasNext()) {
                    FileItem fi = (FileItem) it.next();
                    if (!fi.isFormField()) {
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        poe.setFileName(fileName);
                        if (fileName.lastIndexOf("\\") >= 0) {
                            file = new File(filePath
                                    + fileName.substring(fileName.lastIndexOf("\\")));
                        } else {
                            file = new File(filePath
                                    + fileName.substring(fileName.lastIndexOf("\\") + 1));
                        }
                        poe.setFile(file);
                        String[] fileparts = fieldName.split(fileName);
                        if (fileparts.length>1 && fileparts.length<3) {
                            poe.setFileName(fileparts[0]);
                            poe.setPrefix(fileparts[1]);
                        }
                        if (con.createPOE(poe)){
                            request.setAttribute("mainArea", NamingConv.SUCCESS);
                            request.setAttribute("command", "reloadMain");
                            request.setAttribute("type", "POE");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                            dispatcher.forward(request, response);
                        }else{
                            request.setAttribute("mainArea", NamingConv.FAIL);
                            request.setAttribute("command", "reloadMain");
                            request.setAttribute("type", "POE");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                            dispatcher.forward(request, response);
                        }
                    }
                        //----------------------------

                        //----------------------------
                }
                
            } catch (Exception e) {
            }
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
