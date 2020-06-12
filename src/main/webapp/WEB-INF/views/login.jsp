<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <link href="${contextRoot}/resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap DataTable -->
        <link href="${contextRoot}/resources/css/dataTables.bootstrap.css" rel="stylesheet">

        <!-- Bootstrap glyphicons-->
        <link href="${contextRoot}/resources/css/bootstrap-glyphicons.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${contextRoot}/resources/css/myapp.css" rel="stylesheet">

    </head>
    <body>
        <div class="wrapper">
            <!-- Navigation -->
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
				<div class="container">
					<a class="navbar-brand" href="${contextRoot}/home">Home</a>
				</div>
			</nav>
            <!-- Page Content -->
            <div class="content">
				<div class="container">
					<c:if test="${not empty message}">
						<div class="alert alert-danger alert-dismissible fade show" role="alert">
							<strong>${message}</strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
					</c:if>
					<c:if test="${not empty logout}">
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<strong>${logout}</strong>
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
					</c:if>
					<div class="row">
						<div class="col-md-6 offset-md-3">
							<div class="card">
								<div class="card-header">
									<h4>Login</h4>
								</div>
								<div class="card-body">
									<form action="${contextRoot}/login" method="POST" class="form-horizontal" id="loginForm">
										<div class="form-group row">
											<label for="username" class="col-sm-3 col-form-label">Email: </label>
											<div class="col-sm-9">
												<input type="text" name="email" id="email" class="form-control"/>
											</div>
										</div>
										<div class="form-group row">
											<label for="password" class="col-sm-3 col-form-label">Password: </label>
											<div class="col-sm-9">
												<input type="password" name="password" id="password" class="form-control"/>
											</div>
										</div>
										<div class="form-group row">
											<div class="col-md-8 offset-md-3">
												<input type="submit" value="Login" class="btn btn-primary"/>
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											</div>
										</div>
									</form>
								</div>
								<div class="card-footer">
									<div class="text-right">
										New User - <a href="${contextRoot}/registration">Register Here</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
            </div>

            <!-- Footer -->
            <div class="footer">
                <%@include file="shared/footer.jsp"%>
            </div>

            <!-- Bootstrap core JavaScript -->
            <script src="${contextRoot}/resources/js/jquery/jquery.min.js"></script>
            <script src="${contextRoot}/resources/js/bootstrap.bundle.min.js"></script>

            <!-- jQuery Validator-->
            <script src="${contextRoot}/resources/js/jquery.validate.js"></script>

           	<!-- Self coded javascript -->
            <script src="${contextRoot}/resources/js/myapp.js"></script>
        </div>
    </body>
</html>