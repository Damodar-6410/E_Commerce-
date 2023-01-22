<%@include file="info.jsp" %>

<%
 int n=session.getMaxInactiveInterval();
 long val=session.getCreationTime();
 java.util.Date dt=new java.util.Date(val);
Cookie ck[]=request.getCookies();
String userChoice="All";
String userName=(String)session.getAttribute("username");
if(userName==null)
{
    response.sendRedirect("index.jsp");
}
for(Cookie c:ck)
{
    String name=c.getName();
    if(name.equals("choice"))
    {
        userChoice=c.getValue();
    }
    
}
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Buyer Page!</h1><br>
        <h2>Welcome <%=userName%></h2><br>
        <h3>You are on this site since <%=dt.toString()%></h3><br>
        <h4>If You are Idle for <%=n%> Seconds Your Session Will Expire</h4>
        <hr>
        <pre>
            <a href="ShowCategories">Explore-Store</a>
            <a href="prodlist.jsp">View-All-Products</a>
            <a href="">Search-Product</a>
            <a href="DisplayCart">View-Cart</a>
            <a href="">Logout</a>
        </pre>
        <hr><br<<br>
    <marquee><h3>Attractive Discount on <%=userChoice%></h3></marquee>
    </body>
</html>
<%@include file="footer.jsp" %>