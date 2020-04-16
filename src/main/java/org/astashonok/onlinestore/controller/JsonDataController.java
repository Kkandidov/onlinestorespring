package org.astashonok.onlinestore.controller;

import org.astashonok.onlinestorebackend.dao.CategoryDAO;
import org.astashonok.onlinestorebackend.dao.ProductDAO;
import org.astashonok.onlinestorebackend.dto.Product;
import org.astashonok.onlinestorebackend.exceptions.basicexception.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private CategoryDAO categoryDAO;

    @RequestMapping("/all/products")
    @ResponseBody
    public List<Product> getAllProducts(){
        try {
            return productDAO.getAllActive();
        } catch (BackendException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/admin/all/products")
    @ResponseBody
    public List<Product> getAllProductsForAdmin(){
        try {
            return productDAO.getAll();
        } catch (BackendException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/category/{id}/products")
    @ResponseBody
    public List<Product> getProductsByCategory(@PathVariable long id) {
        try {
            return productDAO.getAllActiveByCategory(categoryDAO.getById(id));
        } catch (BackendException e) {
            e.printStackTrace();
        }
        return null;
    }
}
