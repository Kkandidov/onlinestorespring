package org.astashonok.onlinestore.controller;

import org.astashonok.onlinestore.model.UserModel;
import org.astashonok.onlinestore.util.ClassName;
import org.astashonok.onlinestorebackend.dao.CartDAO;
import org.astashonok.onlinestorebackend.dao.UserDAO;
import org.astashonok.onlinestorebackend.dto.User;
import org.astashonok.onlinestorebackend.exceptions.basicexception.BackendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalController {

    private static final Logger logger = LoggerFactory.getLogger(ClassName.getCurrentClassName());

    @Autowired
    private HttpSession session;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CartDAO cartDAO;

    @ModelAttribute("userModel")
    public UserModel getUserModel() {
        logger.info("Invoking of UserModel getUserModel()");
        if (session.getAttribute("userModel") == null) {
            logger.info("userModel = null");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            try {
                User user = userDAO.getByEmail(authentication.getName());
                logger.info("User = {}", user);
                if (user != null) {
                    UserModel userModel = new UserModel();
                    userModel.setId(user.getId());
                    userModel.setFullName(user.getFirstName() + " " + user.getLastName());
                    userModel.setEmail(user.getEmail());
                    userModel.setRole(user.getRole().getName());
                    if (userModel.getRole().equals("USER")) {
                        userModel.setCart(cartDAO.getByUser(user));
                    }
                    session.setAttribute("userModel", userModel);
                    logger.info("UserModel = {}", user);
                    return userModel;
                }
            } catch (BackendException e) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                e.printStackTrace(printWriter);
                logger.error(stringWriter.toString());
            }
        }
        logger.info("UserModel = {}", session.getAttribute("userModel"));
        return (UserModel) session.getAttribute("userModel");
    }
}
