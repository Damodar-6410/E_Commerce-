<%-- Hello This is Comment  --%>

<%
    session.setMaxInactiveInterval(150);
String v1="",v2="";
Cookie ck[]=request.getCookies();
if(ck!=null)
{
       for(int i=0;i<ck.length;i++)
       {
           Cookie c=ck[i];
           String name=c.getName();
           if(name.equals("uid"))
           {
               v1=c.getValue();
           }
           else if(name.equals("pwd"))
           {
               v2=c.getValue();
           }
       }
}

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Online Shopping!</h1>
        <hr>
        <form action="VerifyUser">
            <pre>
                UserId             <input type="text" name="userid" value="<%=v1%>"/><br>
                Password           <input type="Password" name="password" value="<%=v2%>"/><br>
                UserType           <select name="usertype">
                                      <option>Buyer</option>
                                      <option>Admin</option>
                                   </select><br>
                Save Password      <input type="checkbox" name="Save" value="yes"/><br>
                               <input type="submit" value="Login"/>
            </pre>            
        </form>
        <hr><br><br>
        <a href="Registration.jsp">New User</a>
    </body>
</html>
