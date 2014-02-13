<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Your cart contains: <br>
    
        <c:forEach var="cartItem" items="${cart.items}" varStatus="iter">

              <c:set var="product" value="${cartItem.product}"/>

                ${product.name} ${cartItem.quantity}
                <form name="change quantity" method="post" action="changeQuantity" >
                    <input name= "quantity" type="text" value="change quantity">
                    <input type="SUBMIT" value="change quantity">
                    <input type="hidden"
                        name="productId"
                        value="${product.id}">
                </form>
                <form name="remove" method="post" action="removeFromCart" >
                    <input type="SUBMIT" value="remove product from cart">
                    <input type="hidden"
                        name="productId"
                        value="${product.id}">
                </form><br>
              

        </c:forEach>

                           
                           
    </body>
</html>
