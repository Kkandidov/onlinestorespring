package org.astashonok.onlinestore.controller;

import org.astashonok.onlinestore.util.ClassName;
import org.astashonok.onlinestore.util.FileUpload;
import org.astashonok.onlinestore.validator.ProductValidator;
import org.astashonok.onlinestorebackend.dao.*;
import org.astashonok.onlinestorebackend.dto.*;
import org.astashonok.onlinestorebackend.exceptions.basicexception.BackendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/manage")
public class ManagementController {

    private static final Logger logger = LoggerFactory.getLogger(ClassName.getCurrentClassName());

    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private DescriptionDAO descriptionDAO;
    @Autowired
    private BrandDAO brandDAO;
    @Autowired
    private ViewDAO viewDAO;
    @Autowired
    private ProductValidator productValidator;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView manageProductGet(@RequestParam(name = "operation", required = false) String operation) {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("title", "Manage Products");
        modelAndView.addObject("manageProductClicked", true);
        Product nProduct = new Product();
        modelAndView.addObject("product", nProduct);
        if (operation != null) {
            if (operation.equals("product")) {
                modelAndView.addObject("message", "Product Submitted Successfully!");
            } else if (operation.equals("category")) {
                modelAndView.addObject("message", "Category Submitted Successfully!");
            }
        }
        return modelAndView;
    }

    @RequestMapping("/{id}/product")
    public ModelAndView manageProductEdit(@PathVariable long id) throws BackendException {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("title", "Manage Products");
        modelAndView.addObject("manageProductClicked", true);
        Product product = productDAO.getById(id);
        product.setDescription(descriptionDAO.getByProduct(product));
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String manageProductPost(@ModelAttribute("product") Product mProduct
            , BindingResult result, Model model, HttpServletRequest request) throws BackendException {
        if (mProduct.getId() == 0) {
            productValidator.validate(mProduct, result);
        } else if (!Objects.equals(mProduct.getFile().getOriginalFilename(), "")) {
            productValidator.validate(mProduct, result);
        }
        if (result.hasErrors()) {
            model.addAttribute("manageProductClicked", true);
            model.addAttribute("title", "Manage Products");
            model.addAttribute("message", "Validation fails for Product Submission!");
            return "page";
        }
        if (mProduct.getId() == 0) {
            productDAO.add(mProduct);
            descriptionDAO.edit(mProduct.getDescription());
            for (View view : mProduct.getViews()) {
                if (view.getFile() != null && !Objects.equals(view.getFile().getOriginalFilename(), "")) {
                    viewDAO.add(view);
                }
            }
        } else {
            productDAO.edit(mProduct);
            descriptionDAO.edit(mProduct.getDescription());
            for (View view : mProduct.getViews()) {
                if (view.getFile() != null && !Objects.equals(view.getFile().getOriginalFilename(), "")) {
                    viewDAO.add(view);
                }
            }
        }
        if (!Objects.equals(mProduct.getFile().getOriginalFilename(), "")) {
            FileUpload.uploadFile(request, mProduct.getFile(), mProduct.getCode(), mProduct.getId());
        }
        for (View view : mProduct.getViews()) {
            if (view.getFile() != null && !Objects.equals(view.getFile().getOriginalFilename(), "")) {
                FileUpload.uploadFile(request, view.getFile(), view.getCode(), mProduct.getId());
            }
        }
        return "redirect:/manage/product?operation=product";
    }

    @RequestMapping(value = "/product/{id}/activation", method = RequestMethod.GET)
    @ResponseBody
    public String manageProductActivation(@PathVariable long id) throws BackendException {
        Product product = productDAO.getById(id);
        boolean isActive = product.isActive();
        product.setActive(!isActive);
        productDAO.edit(product);
        return (isActive) ? "Product Deactivated Successfully!" : "Product Activated Successfully";
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        try {
            return categoryDAO.getAllActive();
        } catch (BackendException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ModelAttribute("category")
    public Category getCategory() {
        return new Category();
    }

    @ModelAttribute("brands")
    public List<Brand> getBrands() {
        try {
            return brandDAO.getAllActive();
        } catch (BackendException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ModelAttribute("brand")
    public Brand getBrand() {
        return new Brand();
    }
}
