
package shoppkg;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowDetail extends HttpServlet {
 Connection con;PreparedStatement ps;ResultSet rs;
 
 @Override
    public void init() throws ServletException {
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
          con=DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineShop","root","root");
          String qr="select * from prodinfo where pcode=?";
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
      String id=request.getParameter("pid");
       try
      {
             Class.forName("com.mysql.jdbc.Driver");
          con=DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineShop","root","root");
          String qr="select * from prodinfo where pcode=?";
          ps=con.prepareStatement(qr);
          ps.setString(1,id);
          rs=ps.executeQuery();
         out.println("<html>");
         out.println("<body>");
         out.println("<h1>Select Desired Product</h1>");
         rs.next();
         String s1=rs.getString(1);
         String s2=rs.getString(2);
         String s3=rs.getString(3);
         String s4=rs.getString(4);
         String s5=rs.getString(5);
         out.println("<br>");
         out.println("<hr>");
         out.println("<br>");
         out.println("<table border=2>");
         out.println("<tr>");
         out.println("<td>Product Code</td>");
         out.println("<td>"+s1+"</td>");
         out.println("</tr>");
         out.println("<tr>");
         out.println("<td>Product Name</td>");
         out.println("<td>"+s2+"</td>");
         out.println("</tr>");
         out.println("<tr>");
         out.println("<td>Description</td>");
         out.println("<td>"+s3+"</td>");
         out.println("</tr>");
         out.println("<tr>");
         out.println("<td>Category</td>");
         out.println("<td>"+s4+"</td>");
         out.println("</tr>");
         out.println("<tr>");
         out.println("<td>Price</td>");
         out.println("<td>"+s5+"</td>");
         out.println("</tr>");
         out.println("</table>");
         out.println("<br>");
         out.println("<hr>");
         out.println("<br>");
         out.println("<a href=CartManager?pid="+s1+">Add-To-Cart</a><br>");
         out.println("<a href=ShowCategories>CategoryPage</a><br>");
         out.println("<a href=buyerhome.jsp>Buyer-Home</a>");
         out.println("</body>");
         out.println("</html>");
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
