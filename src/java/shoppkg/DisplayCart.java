
package shoppkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisplayCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        ArrayList list=(ArrayList)session.getAttribute("cart");
        out.println("<html><body>");
        if(list==null)
        {
             out.println("<h3>Your cart is empty</h3>");
             out.println("<h4><a href=ShowCategories>Start-Buying</a></h4>");
        }
        else
        {
            out.println("<h3>Your Cart:</h3>");
            String items=list.toString();
            items=items.replace("[","(");
            items=items.replace("]",")");
            String qr="select * from prodinfo where pcode in"+items;
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineShop","root","root");
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery(qr);
                out.println("<table border=2>");
                out.println("<tr>");
                out.println("<td>Pcode</td><td>Title</td><td>Description</td><td>Category</td><td>Price</td><td>Remove</td>");
                out.println("</tr>");
                int sum=0;
                while(rs.next())
                {
                    String s1=rs.getString(1);
                    String s2=rs.getString(2);
                    String s3=rs.getString(3);
                    String s4=rs.getString(4);
                    String s5=rs.getString(5);
                    sum=sum+Integer.parseInt(s5);
                    out.println("<tr>");
                    out.println("<td>"+s1+"</td>");
                    out.println("<td>"+s2+"</td>");
                    out.println("<td>"+s3+"</td>");
                    out.println("<td>"+s4+"</td>");
                    out.println("<td align=right>"+s5+"</td>");
                    out.println("<td align=center><a href=RemoveCart?id="+s1+">[X]</a></td>");
                    out.println("</tr>");
                }
                out.println("<tr>");
                out.println("<td></td><td></td><td></td><td>Total</td><td>"+sum+"</td>");
                out.println("</tr>");
                out.println("</table><br><br>");
                out.println("<a href=PaymentPage>Confirm-Order</a><br>");
                out.println("<a href=ShowCategories>Add-More-Product</a><br>");
                out.println("<a href=buyerhome.jsp>Buyer-Home</a>");
                
                con.close();
            }
            catch(Exception e)
            {
                
            }
        }
        out.println("</body></html>");
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
