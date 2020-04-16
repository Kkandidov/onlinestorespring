<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container">
    <div class="row">

		<c:if test="${not empty message}">
			<div class="alert alert-success alert-dismissible fade show" role="alert">
				<strong>${message}</strong>
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>

		<div class="col-md-10 offset-md-1">
            <div class="card">
				<div class="card-header">
                    <h4>Product Management</h4>
				</div>
				<div class="card-body">
					<sf:form modelAttribute="product"
					    action="${contextRoot}/manage/product"
						method="POST"
						enctype="multipart/form-data">
						<div class="row">
							<div class="form-group col-md-6">
								<sf:hidden path="id"/>
								<sf:hidden path="code"/>
								<sf:input type="text" path="name" class="form-control" id="name"
								placeholder="Product Name"/>
								<sf:errors path="name" cssClass="help-block" element="em"/>
							</div>
							<div class="form-group col-md-6">
								<sf:input type="text" path="description.screen" class="form-control" id="screen"
								placeholder="Screen (description)"/>
							</div>
						</div>
						<div class="row">
							<label class="form-group col-md-2"><strong>Brand</strong></label>
							<div class="form-group col-md-4">
								<sf:select class="form-control" path="brand.id" items="${brands}"
								itemLabel="name" itemValue="id"/>
							</div>
							<div class="form-group col-md-6">
								<sf:input type="text" path="description.color" class="form-control" id="color"
								placeholder="Color (description)"/>
								<sf:errors path="description.color" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="row">
							<label class="form-group col-md-2"><strong>Price</strong></label>
							<div class="form-group col-md-4">
								<sf:input type="number" path="unitPrice" class="form-control" id="unitPrice"/>
								<sf:errors path="unitPrice" cssClass="help-block" element="em"/>
							</div>
							<div class="form-group col-md-6">
								<sf:input type="text" path="description.processor" class="form-control" id="processor"
								placeholder="Processor (description)"/>
							</div>
						</div>
						<div class="row">
							<label class="form-group col-md-2"><strong>Quantity</strong></label>
							<div class="form-group col-md-4">
								<sf:input type="number" path="quantity" class="form-control" id="quantity"/>
								<sf:errors path="quantity" cssClass="help-block" element="em"/>
							</div>
							<div class="form-group col-md-6">
								<sf:input type="text" path="description.frontCamera" class="form-control" id="frontCamera"
								placeholder="Front Camera (description)"/>
							</div>
						</div>
						<div class="row">
							<label class="form-group col-md-2"><strong>Category</strong></label>
							<div class="form-group col-md-4">
								<sf:select class="form-control" path="category.id" items="${categories}"
								itemLabel="name" itemValue="id"/>
							</div>
							<div class="form-group col-md-6">
								<sf:input type="text" path="description.rearCamera" class="form-control" id="rearCamera"
								placeholder="Rear Camera (description)"/>
							</div>
						</div>
						<div class="row">
							<label class="form-group col-md-2"><strong>Main image</strong></label>
							<div class="form-group col-md-4">
								<sf:input type="file" path="file" class="form-control" id="file"/>
								<sf:errors path="file" cssClass="help-block" element="em"/>
							</div>
							<div class="form-group col-md-6">
								<sf:input type="text" path="description.capacity" class="form-control" id="capacity"
								placeholder="Capacity (description)"/>
								<sf:errors path="description.capacity" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="row">
							<label class="form-group col-md-2"><strong>Minor images</strong></label>
							<div class="form-group col-md-4">
								<sf:input type="file" path="views[0].file" class="form-control"/>
								<sf:errors path="views[0].file" cssClass="help-block" element="em"/>
								</br>
								<sf:input type="file" path="views[1].file" class="form-control"/>
								<sf:errors path="views[1].file" cssClass="help-block" element="em"/>
								</br>
								<sf:input type="file" path="views[2].file" class="form-control"/>
								<sf:errors path="views[2].file" cssClass="help-block" element="em"/>
								</br>
								<sf:input type="file" path="views[3].file" class="form-control"/>
								<sf:errors path="views[3].file" cssClass="help-block" element="em"/>
								</br>
								<sf:input type="file" path="views[4].file" class="form-control"/>
								<sf:errors path="views[4].file" cssClass="help-block" element="em"/>
							</div>
							<div class="form-group col-md-6">
								<sf:input type="text" path="description.battery" class="form-control" id="battery" placeholder="Battery (description)"/>
								<sf:errors path="description.battery" cssClass="help-block" element="em"/>
								</br>
								<sf:input type="text" path="description.displayTechnology" class="form-control" id="displayTechnology" placeholder="Display Technology (description)"/>
								</br>
								<sf:input type="text" path="description.graphics" class="form-control" id="graphics" placeholder="Graphics (description)"/>
								</br>
								<sf:input type="text" path="description.wirelessCommunication" class="form-control" id="wirelessCommunication" placeholder="Wireless Communication (description)"/>
							</div>
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</sf:form>
				</div>
			</div>
		</div>
	</div>

	<hr/>
	<h1>Available Products</h1>
	<hr/>

    <div class="row">
		<div class="col-md-12">
			<div class="container-fluid">
				<div class="table-responsive">
					<table id="adminProductsTable" class="table table-condensed table-bordered">
						<thead>
							<tr>
								<th>Id</th>
								<th>&#160;</th>
								<th>Name</th>
								<th>Brand</th>
								<th>Unit Price</th>
								<th>Quantity</th>
								<th>Active</th>
								<th>Edit</th>
							</tr>
						</thead>

						<tfoot>
							<tr>
								<th>Id</th>
								<th>&#160;</th>
								<th>Name</th>
								<th>Brand</th>
								<th>Unit Price</th>
								<th>Quantity</th>
								<th>Active</th>
								<th>Edit</th>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
        </div>
	</div>
</div>