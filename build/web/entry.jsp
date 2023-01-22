<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="background-color: red;text-align: center">Online Shopping!</h1><br>
        <h2>Product Entry Form!</h2><br><br>
        <hr>
        <form action="SaveProduct">
            <pre>
                  Pcode           <input type="text" name="pcode"/><br>
                  Tittle          <input type="text" name="ptitle"/><br>
                  Description     <input type="text" name="desc"/><br>
                  Category        <select name="cat">
                                      <option>Automobile</option>
                                      <option>Books</option>
                                      <option>Clothing</option>
                                      <option>Hardware</option>
                                      <option>Electronics</option>
                                      <option>Footwear</option>
                                      <option>Furniture</option>
                                      <option>Jewelry</option>
                                      <option>Kitchen</option>
                                     <option>Toy-And-Games</option>
                                   </select><br>
                  Price           <input type="text" name="mobile"/><br>
                            <input type="submit" value="submit"/>
            </pre>
        </form>
        <hr><br><br>
        <a href="adminhome.jsp">Admin-Home</a>
    </body>
</html>
