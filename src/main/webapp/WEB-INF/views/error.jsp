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
					    <div class="col-lg-12">
						    <div class="jumbotron">
							    <h1>${errorTitle}</h1>
							    <hr/>
							    <blockquote class="blockquote" style="word-wrap:break-word">
								    ${errorDescription}
							    </blockquote>						
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