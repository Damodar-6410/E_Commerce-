package shoppkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ShowCategories", urlPatterns = {"/ShowCategories"})
public class ShowCategories extends HttpServlet {
         Connection con;PreparedStatement ps;ResultSet rs;

    @Override
    public void init() throws ServletException {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineShop","root","root");
            String qr="select distinct category from prodinfo order by category";
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
        HttpSession session=request.getSession();
        ArrayList list=(ArrayList)session.getAttribute("cart");
        int no=0;
        if(list!=null)
        {
            no=list.size();
        }
        String name=(String)session.getAttribute("username");
        if(name==null)
        {
            response.sendRedirect("index.jsp");
        }
        try
        {
             rs=ps.executeQuery();
             out.println("<html>");
             out.println("<body>");
             out.println("<h2>Welcome "+name+"</h2>");
             out.println("<h3>Total Number of Product in Cart "+no+"</h3>");
             out.println("<h3  style=background-color: red;text-align: center>Select Desired Category</h3><br><br>");
             out.println("<hr><br>");
             while(rs.next())
             {
                String s1=rs.getString(1);
                out.println("<a href=ShowItems?cat="+s1+">");
                out.println(s1);
                out.println("</a>");
                out.println("<br>");
             }
             out.println("<br><hr>");
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
