The project with Spring, Maven, JS and Bootstrap

Technologies:
	1) servlet: Spring MVC, JavaServer Pages
	2) user authorization: Spring Security
	3) data access: JDBC
	4) REST web service
	5) JUnit, Hamcrest
	6) web interface: jQuery, Bootstrap
	7) database: MySQL
	8) servlet container: Apache Tomcat

Store functionality: 
1) Visual presentation of the assortment of goods
2) Shopping cart: 
  *product selection: add, delete, change quantity
	*view basket contents
	*storage of a registered customer’s basket in the database
3)Store Control Panel:
	*Admin can add new product and edit exist product
4) Secure Access to the Application:
	*user registration and authorization
	*control panel access restriction
	
Form Validation:
	*Validation of data of all forms of the user and administrative interface
	*Regular expressions (regex) are used for character string checking
	*Visualization supplemented by Bootstrap classes
	*Server-side validation is performed using org.springframework.validation

Exception Handling
	*The application implements centralized exception handling 
	*with the class org.astashonok.onlinestore.controller.GlobalExceptionController 
	*with the @ControllerAdvice annotation provided by Spring

Database model (presented in model_online_store.mwb)
	*The application database consists of 12 related tables displayed in 12 classes
	*The data access layer is represented by DAO classes
        
        
        
