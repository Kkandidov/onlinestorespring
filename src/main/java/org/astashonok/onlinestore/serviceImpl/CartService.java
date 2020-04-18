package org.astashonok.onlinestore.serviceImpl;

import org.astashonok.onlinestore.model.UserModel;
import org.astashonok.onlinestore.util.ClassName;
import org.astashonok.onlinestorebackend.dao.CartDAO;
import org.astashonok.onlinestorebackend.dao.CartItemDAO;
import org.astashonok.onlinestorebackend.dao.ProductDAO;
import org.astashonok.onlinestorebackend.dto.Cart;
import org.astashonok.onlinestorebackend.dto.CartItem;
import org.astashonok.onlinestorebackend.dto.Product;
import org.astashonok.onlinestorebackend.exceptions.basicexception.BackendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@Service("cartService")
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(ClassName.getCurrentClassName());

    @Autowired
    private CartItemDAO cartItemDAO;
    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private HttpSession session;
    @Autowired
    private ProductDAO productDAO;

    //return the cart of the user who has logged in
    private Cart getCart() {
        return ((UserModel) session.getAttribute("userModel")).getCart();
    }

    // returns the entire cart items
    public List<CartItem> getCartItems() {
        try {
            return cartItemDAO.getByCart(this.getCart());
        } catch (BackendException e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            logger.error(stringWriter.toString());
        }
        return null;
    }

    public String updateCartItem(long cartItemId, int count) {
        try {
            CartItem cartItem = cartItemDAO.getById(cartItemId);
            if (cartItem == null) {
                return "result=error";
            } else {
                Product product = cartItem.getProduct();
                double oldTotal = cartItem.getTotal();
                if (product.getQuantity() <= count) {
                    return "result=unavailable";
                }
                cartItem.setProductCount(count);
                cartItem.setProductPrice(product.getUnitPrice());
                cartItem.setTotal(product.getUnitPrice() * count);
                cartItemDAO.edit(cartItem);
                Cart cart = this.getCart();
                cart.setTotal(cart.getTotal() - oldTotal + cartItem.getTotal());
                cartDAO.edit(cart);
                return "result=updated";
            }
        } catch (BackendException e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            logger.error(stringWriter.toString());
        }
        return null;
    }

    public String deleteCartItem(long cartItemId) {
        try {
            CartItem cartItem = cartItemDAO.getById(cartItemId);
            if (cartItem == null) {
                return "result=error";
            } else {
                Cart cart = this.getCart();
                cart.setTotal(cart.getTotal() - cartItem.getTotal());
                cart.setCartItems(cart.getCartItems() - 1);
                cartDAO.edit(cart);
                cartItemDAO.remove(cartItem);
                return "result=deleted";
            }
        } catch (BackendException e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            logger.error(stringWriter.toString());
        }
        return null;
    }

    public String addCartItem(long productId) {
        String response = null;
        Cart cart = this.getCart();
        try {
            Product product = productDAO.getById(productId);
            CartItem cartItem = cartItemDAO.getByCartAndProduct(cart, product);
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setCart(cart);
                cartItem.setProduct(product);
                cartItem.setProductPrice(product.getUnitPrice());
                cartItem.setProductCount(1);
                cartItem.setTotal(product.getUnitPrice());
                cartItem.setAvailable(true);
                cartItemDAO.add(cartItem);
                cart.setCartItems(cart.getCartItems() + 1);
                cart.setTotal(cart.getTotal() + cartItem.getTotal());
                cartDAO.edit(cart);
                response = "result=added";
            } else {
                if (cartItem.getProductCount() < 3) {
                    response = this.updateCartItem(cartItem.getId(), cartItem.getProductCount() + 1);
                } else {
                    response = "result=maximum";
                }
            }
        } catch (BackendException e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            logger.error(stringWriter.toString());
        }
        return response;
    }
}

