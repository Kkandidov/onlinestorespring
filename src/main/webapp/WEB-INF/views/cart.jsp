<div class="container">
	<c:if test="${not empty message}">
		<div class="alert alert-info alert-dismissible fade show" role="alert">
			<strong>${message}</strong>
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>
	<c:choose>
		<c:when test="${not empty cartItems}">
			<table id="cart" class="table table-hover table-condensed">
    			<thead>
					<tr>
						<th style="width:10%">Product</th>                                       
						<th style="width:40%"></th>
						<th style="width:10%">Price</th>
						<th style="width:8%">Quantity</th>
						<th style="width:18%" class="text-center">Subtotal</th>
						<th style="width:14%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cartItems}" var="cartItem">
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs">
										<img src="/resources/images/products/${cartItem.product.id}/${cartItem.product.code}.jpeg" alt="${cartItem.product.name}" class="img-responsive cartImg"/>
									</div>
								</div>
							</td>
							<td>
								<h4 class="nomargin">${cartItem.product.name}
									<c:if test="${cartItem.available == false}">
										<strong class = "unavailable">
											(Not Available) 
										</strong>
									</c:if>
								</h4>
								<p>Brand - ${cartItem.product.brand.name}</p>
							</td>
							<td data-th="Price">$ ${cartItem.productPrice}</td>
							<td data-th="Quantity">
								<input type="number" id="count_${cartItem.id}" min="1" max="3" class="form-control text-center" value="${cartItem.productCount}" >
							</td>
							<td data-th="Subtotal" class="text-center">$ ${cartItem.total}</td>
							<td class="actions text-center" data-th="">
								<button type="button" name="refreshCart" value="${cartItem.id}" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-refresh"></span></button>
								<a href="${contextRoot}/cart/${cartItem.id}/delete"  class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span></button>								
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2"><a href="${contextRoot}/show/all/products" class="btn btn-warning"><span class="glyphicon glyphicon-chevron-left"></span> Continue Shopping</a></td>
						<td colspan="1" class="hidden-xs"></td>
						<td colspan="2" class="hidden-xs text-center"><strong>Total ${userModel.cart.total}</strong></td>
						<td><a href="#" class="btn btn-success btn-block">Checkout <span class="glyphicon glyphicon-chevron-right"></span></a></td>
					</tr>
				</tfoot>
			</table>
		</c:when>
		<c:otherwise>
			<div class="jumbotron">
				<div class="text-center">
					<h1>
						Your cart is empty!
					</h1>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>