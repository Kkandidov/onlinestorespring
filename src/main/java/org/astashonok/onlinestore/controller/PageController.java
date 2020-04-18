package org.astashonok.onlinestore.controller;

import org.astashonok.onlinestore.exception.ProductNotFoundException;
import org.astashonok.onlinestore.model.UserAutoModel;
import org.astashonok.onlinestore.service.SecurityService;
import org.astashonok.onlinestore.service.UserAutoModelService;
import org.astashonok.onlinestore.validator.UserAutoModelValidator;
import org.astashonok.onlinestorebackend.dao.CategoryDAO;
import org.astashonok.onlinestorebackend.dao.DescriptionDAO;
import org.astashonok.onlinestorebackend.dao.ProductDAO;
import org.astashonok.onlinestorebackend.dao.ViewDAO;
import org.astashonok.onlinestorebackend.dto.Address;
import org.astashonok.onlinestorebackend.dto.Category;
import org.astashonok.onlinestorebackend.dto.Product;
import org.astashonok.onlinestorebackend.dto.User;
import org.astashonok.onlinestorebackend.exceptions.basicexception.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PageController {

    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private DescriptionDAO descriptionDAO;
    @Autowired
    private ViewDAO viewDAO;
    @Autowired
    private UserAutoModelService userAutoModelService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserAutoModelValidator userAutoModelValidator;

    @RequestMapping(value = {"/", "/home", "/index"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("page");
        try {
            modelAndView.addObject("title", "Home");
            modelAndView.addObject("categories", categoryDAO.getAllActive());
            modelAndView.addObject("homeClicked", true);
        } catch (BackendException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("title", "About Us");
        modelAndView.addObject("aboutClicked", true);
        return modelAndView;
    }

    @RequestMapping(value = "/contact")
    public ModelAndView contact() {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("title", "Contact Us");
        modelAndView.addObject("contactClicked", true);
        return modelAndView;
    }

    @RequestMapping(value = "/show/all/products")
    public ModelAndView showAllProducts() {
        ModelAndView modelAndView = new ModelAndView("page");
        try {
            modelAndView.addObject("title", "All Products");
            modelAndView.addObject("categories", categoryDAO.getAllActive());
            modelAndView.addObject("allProductsClicked", true);
        } catch (BackendException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/show/category/{id}/products")
    public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("page");
        try {
            Category category = categoryDAO.getById(id);
            modelAndView.addObject("title", category.getName());
            modelAndView.addObject("categories", categoryDAO.getAllActive());
            modelAndView.addObject("category", category);
            modelAndView.addObject("categoryProductsClicked", true);
        } catch (BackendException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/show/{id}/product")
    public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
        ModelAndView modelAndView = new ModelAndView("page");
        try {
            Product product = productDAO.getById(id);
            if (product == null) {
                throw new ProductNotFoundException();
            }
            modelAndView.addObject("title", product.getName());
            modelAndView.addObject("product", product);
            modelAndView.addObject("description", descriptionDAO.getByProduct(product));
            modelAndView.addObject("views", viewDAO.getByProduct(product));
            modelAndView.addObject("showSingleProductClicked", true);
            modelAndView.addObject("userClickShowProduct", true);
        } catch (BackendException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView register(Model model) {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("title", "Registration");
        model.addAttribute("userAutoModel", new UserAutoModel(new User(), new Address()));
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute("userAutoModel") UserAutoModel userAutoModel
            , BindingResult bindingResult) {
        userAutoModelValidator.validate(userAutoModel, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userAutoModelService.save(userAutoModel);
        User user = userAutoModel.getUser();
        securityService.autoLogIn(user.getEmail(), user.getConfirmPassword());
        return "redirect:/welcome?firstName=" + user.getFirstName() + "&lastName=" + user.getLastName();
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(name = "error", required = false) String error,
                              @RequestParam(name = "logout", required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView("login");
        if (error != null) {
            modelAndView.addObject("message", "Invalid Username and Password");
        }
        if (logout != null) {
            modelAndView.addObject("logout", "User has successfully log out");
        }
        modelAndView.addObject("title", "Login");
        return modelAndView;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcome(@RequestParam(name = "firstName", required = false) String firstName,
                                @RequestParam(name = "lastName", required = false) String lastName) {
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("title", "Welcome");
        if (firstName != null && lastName != null){
            modelAndView.addObject("firstName", firstName);
            modelAndView.addObject("lastName", lastName);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/access-denied")
    public ModelAndView accessDenied(){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("title", "403 - Access Denied");
        modelAndView.addObject("errorTitle", "Aha! Caught You!");
        modelAndView.addObject("errorDescription", "You are not authorized to view this page!");
        return modelAndView;
    }

    @RequestMapping(value = "/perform-logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
    }
}
