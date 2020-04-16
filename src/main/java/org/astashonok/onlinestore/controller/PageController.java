package org.astashonok.onlinestore.controller;

import org.astashonok.onlinestore.exception.ProductNotFoundException;
import org.astashonok.onlinestorebackend.dao.CategoryDAO;
import org.astashonok.onlinestorebackend.dao.DescriptionDAO;
import org.astashonok.onlinestorebackend.dao.ProductDAO;
import org.astashonok.onlinestorebackend.dao.ViewDAO;
import org.astashonok.onlinestorebackend.dto.Category;
import org.astashonok.onlinestorebackend.dto.Product;
import org.astashonok.onlinestorebackend.exceptions.basicexception.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = {"/", "/home", "/index"})
    public ModelAndView index(){
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
    public ModelAndView about(){
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("title", "About Us");
        modelAndView.addObject("aboutClicked", true);
        return modelAndView;
    }

    @RequestMapping(value = "/contact")
    public ModelAndView contact(){
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("title", "Contact Us");
        modelAndView.addObject("contactClicked", true);
        return modelAndView;
    }

    @RequestMapping(value = "/show/all/products")
    public ModelAndView showAllProducts(){
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
    public ModelAndView showCategoryProducts(@PathVariable("id") int id){
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
            if (product == null){
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
}
