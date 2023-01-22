<%-- 
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>           
<%@page import="java.sql.Connection,java.sql.ResultSet,java.sql.DriverManager,java.sql.Statement" %>
--%>

<%@page import="java.sql.*" contentType="text/html" language="java" %>   
<%!
    private int data;
    int getTax(int price)
    {
        int tax=0;
        if(price>=10000){
            tax=price*10/100;
        }else{
            tax=price*5/100;
        }
        return tax;
    }
%>
<%
    //private int data;
    //int data;
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineShop","root","root");
Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select * from prodinfo order by ptitle");

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="text-align:center;background-color:yellow">Product-List!</h1>
        <table border="2" align="center" style="background-color:lightskyblue;color:black;text-align:left;height:60%;width:78%;font-size:25px">
            <tr>
                <td>Product code</td>
                <td>Product Title</td>
                <td>Description</td>
                <td>Category</td>
                <td>Price</td>
                <td>Tax</td>
            </tr>
            <%
                while(rs.next())
                {
                    String s1=rs.getString(1);
                    String s2=rs.getString(2);
                    String s3=rs.getString(3);
                    String s4=rs.getString(4);
                    String s5=rs.getString(5);
            %>
            <tr>
                <td><%=s1%></td>
                <td><%=s2%></td>
                <td><%=s3%></td>
                <td><%=s4%></td>
                <td><%=s5%></td>
                <td><%=getTax(Integer.parseInt(s5))%></td>
            </tr>
             <%
                 }
                con.close();
            %>
        </table>
    </body>
</html>
