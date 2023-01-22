
package shoppkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowItems extends HttpServlet {
         Connection con;PreparedStatement ps;ResultSet rs;

    @Override
    public void init() throws ServletException {
       try
       {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineShop","root","root");
            String qr="select pcode,ptitle from prodinfo where category=?";
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
        String cat=request.getParameter("cat");
        Cookie ck=new Cookie("choice", cat);
        ck.setMaxAge(60*60*24*3);
        response.addCookie(ck);
        try
        {
            ps.setString(1,cat);
            rs=ps.executeQuery();
           
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>Select Desired Product</h3>");
            out.println("<hr><br>");
            while(rs.next())
            {
                String s1=rs.getString(1);
                String s2=rs.getString(2);
                out.println("<a href=ShowDetail?pid="+s1+">");
                out.println(s2);
                out.println("</a>");
                out.println("<br>");
            }
            out.println("<br><hr><br>");
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
