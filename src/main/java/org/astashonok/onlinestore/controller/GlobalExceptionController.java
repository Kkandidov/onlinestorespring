package org.astashonok.onlinestore.controller;

import org.astashonok.onlinestore.exception.ProductNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handlerNoHandlerFoundException() {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorTitle", "The page is not constructed!");
        modelAndView.addObject("errorDescription", "The page you are looking for is not "
                + "available now!");
        modelAndView.addObject("title", "404 Error Page");

        return modelAndView;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handlerProductNotFoundException() {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorTitle", "Product is not available!");
        modelAndView.addObject("errorDescription", "The product you are looking for is not "
                + "available right now!");
        modelAndView.addObject("title", "Product Unavailable");

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");

        /* only for debugging your application*/
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);

        modelAndView.addObject("errorTitle", "Contact Your Administrator!");
        modelAndView.addObject("errorDescription", stringWriter.toString());
        modelAndView.addObject("title", "Error");

        return modelAndView;
    }
}
