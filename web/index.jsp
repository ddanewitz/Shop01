<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>



<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/shoooop01.css">
    <title>Shop 01</title>
</head>
<body>
    <div id="main">
        

        <div id="indexLeftColumn">
            <c:forEach var="category" items="${categories}">

                <a href="<c:url value='selectCategory?${category.id}'/>" method="get">
                    
                    <span class="categoryText">
                        ${category.name}
                    </span>
                </a>

            </c:forEach>
            
            <form name="form1" method="get" action="searchProduct">
                <input type="text" name="searchString" maxlength="20" size="17">
                <input type="SUBMIT" name="Search" value="Search">
            </form>
            
            
            
        </div>

        <div id="indexRightColumn">
            
            <c:forEach var="product" items="${categoryProducts}" varStatus="iter">
                
                <td>
                    <a href="<c:url value='productInfo?${product.id}'/>" method="get">
                        <span>
                            ${product.name}
                        </span>
                    </a>
                        
                    <br>
                </td>
            </tr>
            </c:forEach>
            

                    <c:forEach var="foundProduct" items="${searchProducts}" varStatus="iter">

                        <td>
                            <a href="<c:url value='productInfo?${foundProduct.id}'/>" method="get">
                                <span>
                                    ${foundProduct.name}
                                </span>
                            </a>
                            <br>
                        </td>
                    </tr>
                    </c:forEach>

            
            
        </div>

        
    </div>
</body>