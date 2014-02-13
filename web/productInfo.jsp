<%-- 
    Document   : productInfo
    Created on : 09.02.2014, 00:01:19
    Author     : dima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/shoooop01.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="main">
            

            <div id="productInfo">
                <table style="width:300px">
                    <tr>
                      <td>${selectedProduct.name}</td>
                    </tr>
                    <tr>
                      <td>${selectedProduct.price} €</td>
                    </tr>
                    <tr>
                      <td>${selectedProduct.description}</td>
                    </tr>
                </table>
                <form name="form2" method="post" action="addToCart">
                    <input type="SUBMIT" name="add to cart" value="add to cart">
                    <input type="hidden"
                            name="productId"
                            value="${selectedProduct.id}">

                </form>
                            
                <form name="toIndex" method="get" action="index" >
                    <input type="SUBMIT" value="to Index">
                </form>
                            
                <form name="toCart" method="get" action="shoppingCart" >
                    <input type="SUBMIT" value="to Shopping Cart">
                </form>
            </div>
        </div>    
        
    </body>
</html>
