<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Online Store">
        <meta name="author" content="Konstantin Astashonok">

        <title>Online Store - ${title}</title>

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
	        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	            <div class="container">
	                <div class="navbar-header">
	                    <a class="navbar-brand" href="${contextRoot}/home">Home</a>
	                </div>
			    </div>
		    </nav>	
			<div class="content">
				<div class="col-md-8 offset-md-2">
					<div class="card">
						<div class="card-header">
							<h4><strong>Registration<strong></h4>
						</div>
						<div class="card-body">
							<sf:form modelAttribute="userAutoModel"
					        action="${contextRoot}/registration"
							method="POST">
								<strong>Personal Information</strong>
								<div class="form-row">
									<div class="col-md-4 mb-3">
										<sf:input type="text" path="user.firstName" class="form-control" placeholder="First name"/>
										<sf:errors path="user.firstName" cssClass="help-block" element="em"/>
									</div>
									<div class="col-md-4 mb-3">
										<sf:input type="text" path="user.lastName" class="form-control" placeholder="Last name"/>
									</div>
									<div class="col-md-4 mb-3">
										<sf:input type="text" path="user.email" class="form-control" placeholder="Email"/>
										<sf:errors path="user.email" cssClass="help-block" element="em"/>
									</div>
								</div>
								<div class="form-row">
									<div class="col-md-4 mb-3">
										<sf:input type="text" path="user.contactNumber" class="form-control" placeholder="Contact number"/>
										<sf:errors path="user.contactNumber" cssClass="help-block" element="em"/>
									</div>
									<div class="col-md-4 mb-3">
										<sf:input type="password" path="user.password" class="form-control" placeholder="Password"/>
										<sf:errors path="user.password" cssClass="help-block" element="em"/>
									</div>
									<div class="col-md-4 mb-3">
										<sf:input type="password" path="user.confirmPassword" class="form-control" placeholder="Confirm password"/>
										<sf:errors path="user.confirmPassword" cssClass="help-block" element="em"/>
									</div>
								</div>
								<strong>Billing Address</strong>
								<div class="form-row">
									<div class="col-md-4 mb-3">
										<sf:input type="text" path="billing.lineOne" class="form-control" placeholder="Line one"/>
										<sf:errors path="billing.lineOne" cssClass="help-block" element="em"/>
									</div>
									<div class="col-md-4 mb-3">
										<sf:input type="text" path="billing.lineTwo" class="form-control" placeholder="Line two"/>
									</div>
									<div class="col-md-4 mb-3">
										<sf:input type="text" path="billing.city" class="form-control" placeholder="City"/>
										<sf:errors path="billing.city" cssClass="help-block" element="em"/>
									</div>
								</div>
								<div class="form-row">
									<div class="col-md-4 mb-3">
										<sf:input type="text" path="billing.state" class="form-control" placeholder="State"/>
										<sf:errors path="billing.state" cssClass="help-block" element="em"/>
									</div>
									<div class="col-md-4 mb-3">
										<sf:input type="text" path="billing.country" class="form-control" placeholder="Country"/>
										<sf:errors path="billing.country" cssClass="help-block" element="em"/>
									</div>
									<div class="col-md-4 mb-3">
										<sf:input type="text" path="billing.postalCode" class="form-control" placeholder="Postal code"/>
										<sf:errors path="billing.postalCode" cssClass="help-block" element="em"/>
									</div>
								</div>
								<button class="btn btn-primary" type="submit">Register</button>
							</sf:form>
						</div>
					</div>
				</div>
			</div>
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
	
