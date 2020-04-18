$(function() {

    // solving the active menu problem
    switch (menu) {
        case 'About Us':
            $('#about').addClass('active');
            break;
        case 'Contact Us':
            $('#contact').addClass('active');
            break;
        case 'All Products':
            $('#listProducts').addClass('active');
            break;
        case 'Manage Products':
            $('#manageProduct').addClass('active');
            break;
        default:
            if (menu == "Home") break;
            $('#listProducts').addClass('active');
            $('#a_' + menu).addClass('active');
            break;
    }

    // code for jquery dataTable (Custom table)
    var $table = $('#productListTable');
	if($table.length){
	    var jsonUrl = '';
        if (window.categoryId == '') {
            jsonUrl = window.contextRoot + '/json/data/all/products';
        } else {
            jsonUrl = window.contextRoot + '/json/data/category/'
            + window.categoryId + '/products';
        }
            $table.DataTable({
			lengthMenu: [[3,5,10,-1],['3 Records','5 Records','10 Records', 'ALL']],
		    pageLength: 5,
			ajax:{
				url: jsonUrl,
				dataSrc: ''
			},
			columns:[
						{
							data: null,
							render: function(data, type, row){
								return '<img src="' + window.contextRoot+ '/resources/images/products/' + data.id + '/'
								+ data.code + '.jpeg" class="dataTableImg"/>';
							}
						},
						{
							data: 'name'
						},
						{
							data: 'brand'
						},
						{
							data: 'unitPrice',
							mRender: function(data, type, row){
								return '$ ' + data;
							}
						},
						{
							data: 'quantity',
							mRender: function(data, type, row){
								if(data < 1){
									return '<span style=color:red>Out of Stock!</span>';
								}
								return data;
							}
						},
						{
							data: 'id',
							bSortable: false,
							render: function(data, type, row){
								var str = '';
								str += '<a href="' + window.contextRoot + '/show/' + data + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open" aria-label="Left Align"></span></a>';
								if(userRole == 'ADMIN'){
									str += '<a href="' + window.contextRoot + '/manage/' + data + '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil" aria-label="Left Align"></span></a>';
								} else {
									if(row.quantity < 1) {
									str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart" aria-label="Left Align"></span></a>';
									}else{
										str += '<a href="' + window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart" aria-label="Left Align"></span></a>';
									}
								}
								return str;
							}
						}
					]
			});
        }

    // code for jquery dataTable (Admin table)

    var $adminTable = $('#adminProductsTable');
	if($adminTable.length){
	    var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
            $adminTable.DataTable({
			lengthMenu: [[10,30,50,-1],['10 Records','30 Records','50 Records', 'ALL']],
		    pageLength: 30,
			ajax:{
				url: jsonUrl,
				dataSrc: ''
			},
			columns:[
			        {
			             data: 'id'
			        },
					{
						data: null,
						render: function(data, type, row){
							 return '<img src="' + window.contextRoot+ '/resources/images/products/' + data.id + '/'
							 + data.code + '.jpeg" class="adminDataTableImg"/>';
						}
					},
					{
						data: 'name'
					},
					{
						data: 'brand'
					},
					{
						data: 'unitPrice',
						mRender: function(data, type, row){
							 return '$ ' + data;
						}
					},
					{
						data: 'quantity',
                        mRender: function(data, type, row){
                            if(data < 1){
                        	    return '<span style=color:red>Out of Stock!</span>';
                        	}
                        	return data;
                        }
					},
					{
						data : 'active',
						bSortable : false,
						mRender : function(data, type, row) {
							var str = '<label class="switch">';
							if(data) {
								str += '<input type="checkbox" value="'+row.id+'" checked="checked">';
							}else {
								str += '<input type="checkbox" value="'+row.id+'">';
							}
								str += '<div class="slider round"> </div></label>';
							return str;
						}
					},
					{
						data: 'id',
						bSortable: false,
						mRender: function(data, type, row){
   						     var str = '';
   						     str += '<a href="'+ window.contextRoot + '/manage/' + data + '/product" class="btn btn-warning">';
                             str += '<span class="glyphicon glyphicon-pencil"></span></a>'
                             return str;
						}
					}
					],

					initComplete: function(){
                        var api = this.api();
                        api.$('.switch input[type="checkbox"]').on('change', function(){
                            var checkbox = $(this);
                            var checked = checkbox.prop('checked');
                            var value = checkbox.prop('value');
							console.log(value);
							var activationUrl = window.contextRoot + '/manage/product/' + value + '/activation';
							var xhttp = new XMLHttpRequest();
                            xhttp.open("GET", activationUrl, true);
							xhttp.send();
							console.log(activationUrl);
                        });

                    }
			});
        }
});

