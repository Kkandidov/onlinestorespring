<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${contextRoot}/home">Online Store</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${contextRoot}/home">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item" id="about">
                    <a class="nav-link" href="${contextRoot}/about">About</a>
                </li>
                <li class="nav-item" id="contact">
                    <a class="nav-link" href="${contextRoot}/contact">Contact</a>
                </li>
                <li class="nav-item" id="listProducts">
                    <a class="nav-link" href="${contextRoot}/show/all/products">View Products</a>
                </li>
				<security:authorize access="hasAuthority('ADMIN')">
					<li id = "manageProduct">
						<a class="nav-link" href="${contextRoot}/manage/product">Manage Products</a>
					</li>
				</security:authorize>
				<security:authorize access="isAnonymous()">
					<li id = "register">
						<a class="nav-link" href="${contextRoot}/registration">Sign Up</a>
					</li>
					<li id = "login">
						<a class="nav-link" href="${contextRoot}/login">Login</a>
					</li>
				</security:authorize>
            </ul>
			<ul class="navbar-nav ml-right">
			<security:authorize access="isAuthenticated()">
				<div class="dropdown">
					<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						${userModel.fullName}
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<security:authorize access="hasAuthority('USER')">
							<a class="dropdown-item">
								<a href="${contextRoot}/cart/show">
									<span class="glyphicon glyphicon-shopping-cart"></span>
									<span class="badge">${userModel.cart.cartItems}</span>
									- $ ${userModel.cart.total}
								</a>
							</a>
							<div class="dropdown-divider"></div>
						</security:authorize>
						<a class="dropdown-item" href="${contextRoot}/perform-logout">Log Out</a>
					</div>
				</div>
			</security:authorize>
		</ul>
        </div>
    </div>
</nav>
<script>
	window.userRole = '${userModel.role}';
</script>