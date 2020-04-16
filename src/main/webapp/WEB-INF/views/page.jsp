<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Online Store">
        <meta name="author" content="Konstantin Astashonok">

        <title>Online Store - ${title}</title>

        <script>
        	window.menu = '${title}';
        	window.contextRoot = '${contextRoot}'
        </script>

        <!-- Bootstrap core CSS -->
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap DataTable -->
        <link href="/resources/css/dataTables.bootstrap.css" rel="stylesheet">

        <!-- Bootstrap glyphicons-->
        <link href="/resources/css/bootstrap-glyphicons.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="/resources/css/myapp.css" rel="stylesheet">

    </head>
    <body>
        <div class="wrapper">
            <!-- Navigation -->
            <%@include file="shared/navbar.jsp"%>

            <!-- Page Content -->
                <div class="content">
                    <!-- Loading the home content -->
                    <c:if test="${homeClicked == true}">
                        <%@include file="home.jsp"%>
                    </c:if>

                    <!-- Loading only when user clicks about -->
                    <c:if test="${aboutClicked == true}">
                        <%@include file="about.jsp"%>
                    </c:if>

                    <!-- Loading only when user clicks contact -->
                    <c:if test="${contactClicked == true}">
                        <%@include file="contact.jsp"%>
                    </c:if>

                    <!-- Loading only when user clicks view products -->
                    <c:if test="${allProductsClicked == true or categoryProductsClicked == true}">
                        <%@include file="listProducts.jsp"%>
                    </c:if>

                    <!-- Loading only when user clicks show product -->
                    <c:if test="${showSingleProductClicked == true}">
                        <%@include file="singleProduct.jsp"%>
                    </c:if>

                    <!-- Loading only when user clicks manage products -->
                    <c:if test="${manageProductClicked == true}">
                        <%@include file="manageProducts.jsp"%>
                    </c:if>
                </div>

            <!-- Footer -->
            <div class="footer">
                <%@include file="shared/footer.jsp"%>
            </div>

            <!-- Bootstrap core JavaScript -->
            <script src="/resources/js/jquery/jquery.min.js"></script>
            <script src="/resources/js/bootstrap.bundle.min.js"></script>

            <!-- DataTable Plugin -->
            <script src="/resources/js/jquery/jquery.dataTables.js"></script>

            <!-- DataTable Bootstrap Script -->
            <script src="/resources/js/dataTables.bootstrap.js"></script>

           	<!-- Self coded javascript -->
            <script src="/resources/js/myapp.js"></script>
        </div>
    </body>
</html>