package org.astashonok.onlinestore.controller;

import org.astashonok.onlinestore.serviceImpl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/show")
    public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
        ModelAndView modelAndView = new ModelAndView("page");
        if (result != null) {
            switch (result){
                case "updated" :
                    modelAndView.addObject("message", "CartItem has been updated successfully");
                    break;
                case "added" :
                    modelAndView.addObject("message", "CartItem has been added successfully!");
                    break;
                case "maximum" :
                    modelAndView.addObject("message", "CartItem has reached to maximum count!");
                    break;
                case "unavailable" :
                    modelAndView.addObject("message", "Product is quantity is not available!");
                    break;
                case "deleted" :
                    modelAndView.addObject("message", "CartItem has been removed successfully!");
                    break;
                case "error" :
                    modelAndView.addObject("message", "Something went wrong!");
                    break;
            }
        }
        modelAndView.addObject("title", "User Cart");
        modelAndView.addObject("showCartClicked", true);
        modelAndView.addObject("cartItems", cartService.getCartItems());
        return modelAndView;
    }

    @RequestMapping("/{cartItemId}/update")
    public String updateCart(@PathVariable long cartItemId, @RequestParam int count) {
        String response = cartService.updateCartItem(cartItemId, count);
        return "redirect:/cart/show?" + response;
    }

    @RequestMapping("/{cartItemId}/delete")
    public String updateCart(@PathVariable long cartItemId){
        String response = cartService.deleteCartItem(cartItemId);
        return "redirect:/cart/show?"+response;
    }

    @RequestMapping("/add/{productId}/product")
    public String addCart(@PathVariable long productId){
        String response = cartService.addCartItem(productId);
        return "redirect:/cart/show?"+response;
    }
}

