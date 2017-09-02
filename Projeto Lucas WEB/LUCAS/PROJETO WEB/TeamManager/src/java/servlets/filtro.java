/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lucas
 */
@WebFilter("/*")
public class filtro extends HttpServlet implements Filter {

    List<String> urls = new ArrayList<>();

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet filtro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet filtro at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urls.add("/TeamManager");
        urls.add("/TeamManager/index.jsp");
        urls.add("/TeamManager/Cadastros/*");
        urls.add("/TeamManager/Listagens/*");
        urls.add("/TeamManager/acao");
        urls.add("/TeamManager/assets/js/bootstrap.js");
        urls.add("/TeamManager/assets/js/jquery.js");
        urls.add("/TeamManager/assets/js/jquery-1.11.1.js");
        urls.add("/TeamManager/assets/css/bootstrap.css");
        urls.add("/TeamManager/assets/css/font-awesome.css");
        urls.add("/TeamManager/assets/js/dataTables.bootstrap.min.js");
        urls.add("/TeamManager/assets/js/jquery.dataTables.min.js");
        // Bootstrap CSS   
        urls.add("/TeamManager/boots/css/bootstrap-theme.css");
        urls.add("/TeamManager/boots/css/bootstrap-theme.min.css");
        urls.add("/TeamManager/boots/css/bootstrap.css");
        urls.add("/TeamManager/boots/css/bootstrap.min.css");
        urls.add("/TeamManager/boots/css/navbar-fixed-top.css");
        urls.add("/TeamManager/boots/css/signin.css");
        urls.add("/TeamManager/boots/css/dataTables.bootstrap.min.css");
        
        urls.add("/TeamManager/boots/css/dataTables.bootstrap.min.css");
        urls.add("/TeamManager/boots/css/jquery.dataTables.min.css");
        urls.add("/TeamManager/boots/css/buttons.bootstrap.min.css");
        urls.add("/TeamManager/boots/css/font-awesome.min.css");
        
        urls.add("/TeamManager/boots/js/jquery-1.12.3.js");
        urls.add("/TeamManager/boots/js/dataTables.bootstrap.min.js");
        urls.add("/TeamManager/boots/js/jquery.dataTables.min.js");
        urls.add("/TeamManager/boots/js/dataTables.buttons.min.js");
        urls.add("/TeamManager/boots/js/buttons.bootstrap.min.js");
        urls.add("/TeamManager/boots/js/jszip.min.js");
        urls.add("/TeamManager/boots/js/pdfmake.min.js");
        urls.add("/TeamManager/boots/js/vfs_fonts.js");
        urls.add("/TeamManager/boots/js/buttons.html5.min.js");
        urls.add("/TeamManager/boots/js/buttons.print.min.js");
        urls.add("/TeamManager/boots/js/buttons.colVis.min.js");
        
        urls.add("/TeamManager/JS/chart/*");
        urls.add("/TeamManager/JS/chart/Chart.js");
        urls.add("/TeamManager/JS/chart/Chart.min.js");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest) request;

        if (urls.contains(req.getRequestURI())) {
            request.setAttribute("parametro", "login");
            chain.doFilter(request, response);
        } else {
            HttpSession sessao = ((HttpServletRequest) request).getSession();

            if (sessao.getAttribute("usuarioLogado") == null) {
                ((HttpServletResponse) response).sendRedirect("index.jsp");
            } else {
                System.out.println("Destino: " + req.getRequestURI());
                chain.doFilter(request, response);
            }
        }
    }
}