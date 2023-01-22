

package shoppkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveProduct extends HttpServlet {
      Connection con;PreparedStatement ps;

    @Override
    public void init() throws ServletException {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineShop","root","root");
            String qr="insert into prodinfo values(?,?,?,?,?)";
            ps=con.prepareStatement(qr);
        }
      catch(Exception e)
      {
          
      }
      
    }

    @Override
    public void destroy() {
       try
       {
           con.close();
       }
       catch(Exception e)
       {
           
       }
    }
      
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          String s1=request.getParameter("pcode");
          String s2=request.getParameter("ptitle");
          String s3=request.getParameter("desc");
          String s4=request.getParameter("cat");
          String s5=request.getParameter("mobile");
     try
     {
         ps.setInt(1,Integer.parseInt(s1));
         ps.setString(2, s2);
         ps.setString(3, s3);
         ps.setString(4, s4);
         ps.setInt(5,Integer.parseInt(s5));
         ps.executeUpdate();
         out.println("<h2>Product Added Successfully</h2>");
         out.println("<h2><a href=adminhome.jsp>Admin-home</a></h2>");
         
     }
     catch(Exception e)
     {
         
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
