/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layer1.presentation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import layer2.domain.Controller;
import layer2.domain.bean.POE;
import layer2.domain.bean.Partner;
import layer2.domain.bean.Project;
import layer2.domain.bean.UserAuthentication;
import layer2.domain.bean.UserInfo;
import layer2.domain.interfaces.NamingConv;
import oracle.sql.BLOB;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//comment
/**
 *
 * @author Bancho
 */
@WebServlet(name = "UIServlet", urlPatterns = {"/UIServlet"})
@MultipartConfig
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
            throws ServletException, IOException, FileUploadException {

        HttpSession session = request.getSession();

        Controller ctrl = (Controller) session.getAttribute(NamingConv.CONTROLLER);
        if (ctrl == null) {
            ctrl = new Controller();
            session.setAttribute(NamingConv.CONTROLLER, ctrl);
        }

        String command = (String) request.getParameter(NamingConv.COMMAND);
        RequestDispatcher dispatcher;

        if (command == null) {
            command = (String) session.getAttribute(NamingConv.COMMAND);
        }

        switch (command) {
            case NamingConv.LOG_IN:
                boolean status = validateCredentials(request, response);
                if (status) {
                    viewProjects(request, response);
                } else {
                    dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            case NamingConv.LOG_OUT:
                logOut(request, response);
                break;

            case NamingConv.UPLOAD:
                upload(request, response);
                break;

            case NamingConv.CREATE_USER:
                createUser(request, response);
                break;

            case NamingConv.CREATE_COMPANY:
                createPartner(request, response);
                break;

            case NamingConv.CREATE_PROJECT:
                createProject(request, response);
                break;

            case NamingConv.SET_BUDGET:
                //not done
                break;

            case NamingConv.RELOAD_MAIN:
                String mainArea = (String) request.getParameter(NamingConv.MAINAREA);
                if (mainArea == null) {
                    mainArea = (String) session.getAttribute(NamingConv.MAINAREA);
                }
                switch (mainArea) {
                    case NamingConv.SET_BUDGET:
                        request.setAttribute(NamingConv.MAINAREA, NamingConv.SET_BUDGET);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.VIEW_BUDGET:
                        request.setAttribute(NamingConv.MAINAREA, NamingConv.VIEW_BUDGET);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.CREATE_COMPANY:
                        request.setAttribute(NamingConv.MAINAREA, NamingConv.CREATE_COMPANY);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.CREATE_USER:
                        request.setAttribute(NamingConv.MAINAREA, NamingConv.CREATE_USER);
                        request.setAttribute(NamingConv.PARTNER_LIST, ctrl.getAllPartners());
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.CREATE_PROJECT:
                        request.setAttribute(NamingConv.MAINAREA, NamingConv.CREATE_PROJECT);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.SEE:
                        request.setAttribute(NamingConv.MAINAREA, NamingConv.SEE);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case NamingConv.PROJECT_OVERVIEW:
                    case NamingConv.PENDING_PROJECTS:
                    case NamingConv.APPROVED_PROJECTS:
                        viewProjects(request, response);
                        break;
                    default:
                        request.setAttribute(NamingConv.MAINAREA, NamingConv.SEE);
                        dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                        dispatcher.forward(request, response);
                        break;

                }
                break;
            default:
                request.setAttribute(NamingConv.MAINAREA, NamingConv.SEE);
                dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                dispatcher.forward(request, response);
                break;
        }
    }

    private boolean validateCredentials(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute(NamingConv.CONTROLLER);
        String username = request.getParameter(NamingConv.USERNAME);
        String password = request.getParameter(NamingConv.PASSWORD);
        boolean logInSuccessful = ctrl.validateCredentials(username, password);
        if (logInSuccessful) {
            UserAuthentication ua = ctrl.getUserAuthentication(username);
            UserInfo user = ua.getUserInfo();
            session.setAttribute(NamingConv.USER, user);
        }
        return logInSuccessful;
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute(NamingConv.CONTROLLER);
        ctrl.closeConnection();
        session.invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void createProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute(NamingConv.CONTROLLER);
        UserInfo currentUser = (UserInfo) session.getAttribute(NamingConv.USER);
        Project newProject = (Project) session.getAttribute(NamingConv.NEW_PROJECT);
        newProject.setPartner(currentUser.getCompany());
        newProject.setStage(NamingConv.PENDING);
        String sdate = (String) session.getAttribute(NamingConv.SDATE);
        String fdate = (String) session.getAttribute(NamingConv.FDATE);
        newProject.setSdate(string2date(sdate));
        newProject.setFdate(string2date(fdate));
        boolean status = ctrl.createProject(newProject);
        if (status) {
            request.setAttribute(NamingConv.MAINAREA, NamingConv.SUCCESS);
        } else {
            request.setAttribute(NamingConv.MAINAREA, NamingConv.FAIL);
        }
        request.setAttribute(NamingConv.TYPE, "project");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void viewProjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(NamingConv.MAINAREA, NamingConv.PROJECT_OVERVIEW);
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute(NamingConv.CONTROLLER);
        UserInfo currentUser = (UserInfo) session.getAttribute(NamingConv.USER);
        ArrayList<Project> allProjects = (ArrayList<Project>) ctrl.getAllProjects();

        if (currentUser.getUrole().equals(NamingConv.ADMIN)) {
            String requsted = request.getParameter(NamingConv.MAINAREA);

            switch (requsted) {
                case NamingConv.PROJECT_OVERVIEW:
                    request.setAttribute(NamingConv.PROJECTS, allProjects);
                    break;

                case NamingConv.PENDING_PROJECTS:
                    ArrayList<Project> pendingProjects = new ArrayList<Project>();
                    for (Project project : allProjects) {
                        if (project.getStage().equals(NamingConv.PENDING)) {
                            pendingProjects.add(project);
                        }
                    }
                    request.setAttribute(NamingConv.PROJECTS, pendingProjects);
                    break;

                case NamingConv.APPROVED_PROJECTS:
                    ArrayList<Project> approvedProjects = new ArrayList<Project>();
                    for (Project project : allProjects) {
                        if (project.getStage().equals(NamingConv.PENDING) == false) {
                            approvedProjects.add(project);
                        }
                    }
                    request.setAttribute(NamingConv.PROJECTS, approvedProjects);
                    break;
            }

        } else if (currentUser.getUrole().equals(NamingConv.PARTNER)) {
            ArrayList<Project> onlyPartnerProjects = new ArrayList<Project>();
            for (Project project : allProjects) {
                if (project.getPartner().getCompanyName().equals(currentUser.getCompany().getCompanyName())) {
                    onlyPartnerProjects.add(project);
                }
            }
            request.setAttribute(NamingConv.PROJECTS, onlyPartnerProjects);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void createPartner(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute(NamingConv.CONTROLLER);
        Partner newPartner = (Partner) session.getAttribute(NamingConv.NEW_PARTNER);
        boolean status = ctrl.createPartner(newPartner);
        if (status) {
            request.setAttribute(NamingConv.MAINAREA, NamingConv.SUCCESS);
        } else {
            request.setAttribute(NamingConv.MAINAREA, NamingConv.FAIL);
        }
        request.setAttribute(NamingConv.TYPE, "partner");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, ServletException, IOException {
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute(NamingConv.CONTROLLER);
        UserAuthentication newUserAth = (UserAuthentication) session.getAttribute(NamingConv.NEW_USER_ATH);
        UserInfo newUserInfo = (UserInfo) session.getAttribute(NamingConv.NEW_USER_INFO);

        boolean status = ctrl.createUserInfo(newUserInfo);
        newUserAth.setUserInfo(newUserInfo);
        boolean status1 = ctrl.createUserAth(newUserAth);

        if (status && status1) {
            request.setAttribute(NamingConv.MAINAREA, NamingConv.SUCCESS);
        } else {
            request.setAttribute(NamingConv.MAINAREA, NamingConv.FAIL);
        }

        request.setAttribute(NamingConv.TYPE, "user");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private java.util.Date string2date(String date) {
        Calendar cal = Calendar.getInstance();
        String[] splitted = date.split("-");
        int year = Integer.parseInt(splitted[0]);
        int month = Integer.parseInt(splitted[1]) - 1;
        int day = Integer.parseInt(splitted[2]);
        cal.set(year, month, day);
        return cal.getTime();
    }

    private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute(NamingConv.CONTROLLER);
        POE poe = new POE();
        Part part = request.getPart("file");
        poe.setPrefix(part.getHeader("content-type"));
        poe.setInStream(part.getInputStream());
        poe.setFileName(part.getSubmittedFileName());
        poe.setProject(ctrl.getProject(1));
        //response.setContentType(contentType);
        //////FilenameUtils.getExtension("file or file path+file");/////////////////
        
        
        
        File file = new File(System.getProperty("user.dir")+poe.getFileName());
        OutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        do {
        count = poe.getInStream().read(buffer);
        out.write(buffer, 0, count);
        }
        while (count == 1024);
        
        if (ctrl.createPOE(poe)) {
            request.setAttribute(NamingConv.MAINAREA, NamingConv.SUCCESS);
            request.setAttribute(NamingConv.COMMAND, NamingConv.RELOAD_MAIN);
            request.setAttribute(NamingConv.TYPE, "POE");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute(NamingConv.MAINAREA, NamingConv.FAIL);
            request.setAttribute(NamingConv.COMMAND, NamingConv.RELOAD_MAIN);
            request.setAttribute(NamingConv.TYPE, "POE");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
            dispatcher.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(UIServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(UIServlet.class.getName()).log(Level.SEVERE, null, ex);
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
