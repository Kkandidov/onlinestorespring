<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
    <head>  
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Online Store">
        <meta name="author" content="Konstantin Astashonok">
    
        <title>Online Store - ${title}</title>
 	
        <!-- Bootstrap core CSS -->
        <link href="${contextRoot}/resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${contextRoot}/resources/css/myapp.css" rel="stylesheet">
    </head>

    <body>
	    <div class="wrapper">
	        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	            <div class="container">
	                <div class="navbar-header">
	                    <a class="navbar-brand" href="${contextRoot}/home">Home</a>
	                </div>
			    </div>
		    </nav>			
			<div class="content">
			    <div class="container">
				    <div class="row">
					    <div class="col-lg-6 offset-md-3">
						    <div class="jumbotron">
							    <h1 class="display-4"><center><strong>${title}<strong></center></h1>
								<c:if test="${pageContext.request.userPrincipal.name != null}">
									<p class="lead"><center>${firstName} ${lastName}!!! We are glad to see you!</center></p>
									<hr/>
									<div class="text-right">
										<center><a href="${contextRoot}/login?logout">Log Out</a></center>
									</div>
								</c:if>
						    </div>
					    </div>					
				    </div>
			    </div>
		    </div>
		    <!-- Footer -->
            <div class="footer">
		        <%@include file="shared/footer.jsp" %>
		    </div>
	    </div>
    </body>
</html>