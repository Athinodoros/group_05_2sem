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
import static java.sql.Types.INTEGER;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import layer2.domain.bean.Comment;
import layer2.domain.bean.POE;
import layer2.domain.bean.Partner;
import layer2.domain.bean.Project;
import layer2.domain.bean.UserAuthentication;
import layer2.domain.bean.UserInfo;
import layer2.domain.interfaces.NamingConv;
import sun.rmi.server.Dispatcher;
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
            throws ServletException, IOException {

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
                        String incom = request.getParameter(NamingConv.NEWCOMMENT);
                        String app = request.getParameter(NamingConv.APPROVED);
                        String updes = request.getParameter(NamingConv.UPDATE_DESCR);

                        String path = request.getServletContext().getRealPath("/");
                        File f = new File(path + ctrl.getProject(Integer.parseInt(request.getParameter("thisProjectID"))).getPartner().getCompanyName() + "\\" + request.getParameter("thisProjectID"));
                        File[] fileli = f.listFiles();
                        session.setAttribute("fli", fileli);
                        session.setAttribute("id", request.getParameter("thisProjectID"));

                        if (incom != null) {
                            if (saveComment(request, response, (Comment) session.getAttribute("inComment"))) {
                                session.removeAttribute("inComment");
                                openOneProject(request, response);
                            } else {
                                request.setAttribute(NamingConv.TYPE, "Comment");
                                request.setAttribute(NamingConv.RELOAD_MAIN, NamingConv.FAIL);
                                dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                                dispatcher.forward(request, response);
                                break;
                            }
                        }
                        if (app != null && app.equals(NamingConv.APPROVED)) {
                            Project tempPr = ctrl.getProject(Integer.valueOf(request.getParameter("thisProjectID")));
                            tempPr.setStage(app);
                            if (ctrl.editProject(tempPr)) {
                                openOneProject(request, response);
                            } else {
                                request.setAttribute(NamingConv.TYPE, "Project approval");
                                request.setAttribute(NamingConv.RELOAD_MAIN, NamingConv.FAIL);
                                dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                                dispatcher.forward(request, response);
                                break;
                            }
                        }
                        if (updes != null && updes.equals(NamingConv.UPDATE_DESCR)) {
                            Project tempPr = ctrl.getProject(Integer.valueOf(request.getParameter("thisProjectID")));
                            ctrl.createComment(new Comment(1, tempPr, (UserInfo) session.getAttribute(NamingConv.USER), tempPr.getDescription() + " <span style=\"font-size: 1.1em; font-weight: 600;\"> Was changed to : </span>" + request.getParameter("newDescription")));
                            tempPr.setDescription(request.getParameter("newDescription"));
                            if (ctrl.editProject(tempPr)) {
                                openOneProject(request, response);
                            } else {
                                request.setAttribute(NamingConv.TYPE, "Project new Description");
                                request.setAttribute(NamingConv.RELOAD_MAIN, NamingConv.FAIL);
                                dispatcher = request.getRequestDispatcher("Dashboard.jsp");
                                dispatcher.forward(request, response);
                                break;
                            }
                        }
                        openOneProject(request, response);
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

    private boolean saveComment(HttpServletRequest request, HttpServletResponse response, Comment cm) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute(NamingConv.CONTROLLER);
        return ctrl.createComment(cm);
    }

    private void openOneProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Controller ctrl = (Controller) session.getAttribute(NamingConv.CONTROLLER);
        UserInfo currentUser = (UserInfo) session.getAttribute(NamingConv.USER);
        ArrayList<Project> allProjects = (ArrayList<Project>) ctrl.getAllProjects();
        String thisProject = request.getParameter("thisProjectID");
        ArrayList<Comment> allComments = (ArrayList<Comment>) ctrl.getAllComments(Integer.valueOf(thisProject));
        session.setAttribute("commentList", allComments);
        session.setAttribute("thisProject", ctrl.getProject(Integer.valueOf(thisProject)));
        request.setAttribute(NamingConv.MAINAREA, NamingConv.SEE);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
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
        String order = request.getParameter("order");

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
                        if (order != null) {
                        if (order.equals("BUGET")) {
                            Collections.sort(pendingProjects, new Comparator<Project>() {
                                @Override
                                public int compare(Project o1, Project o2) {
                                    return o1.getProjectBudget() - o2.getProjectBudget();
                                }
                            });
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
                    if (order != null) {
                        if (order.equals("BUGET")) {
                            Collections.sort(approvedProjects, new Comparator<Project>() {
                                @Override
                                public int compare(Project o1, Project o2) {
                                    return o2.getProjectBudget() - o1.getProjectBudget();
                                }
                            });
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

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String path = request.getServletContext().getRealPath("/");

        Project pr = (Project) session.getAttribute("thisProject");
        File f = new File(path + pr.getPartner().getCompanyName());
        f.mkdir();
        f = new File(f + "\\" + session.getAttribute("id"));
        f.mkdir();
        POE poe = new POE();
        Part part = request.getPart("file");
        poe.setPrefix(part.getHeader("content-type"));
        poe.setInStream(part.getInputStream());
        poe.setFileName(part.getSubmittedFileName());
        poe.setProject(ctrl.getProject(1));
        //response.setContentType(contentType);
        //////FilenameUtils.getExtension("file or file path+file");/////////////////

        File file = new File(f + "\\" + poe.getFileName());
        OutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        do {
            count = poe.getInStream().read(buffer);
            out.write(buffer, 0, count);
        } while (count == 1024);

        request.setAttribute(NamingConv.MAINAREA, NamingConv.SUCCESS);
        request.setAttribute(NamingConv.COMMAND, NamingConv.RELOAD_MAIN);
        request.setAttribute(NamingConv.TYPE, "POE");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);

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
