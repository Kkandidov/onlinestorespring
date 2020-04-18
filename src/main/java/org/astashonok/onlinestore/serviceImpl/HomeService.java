package org.astashonok.onlinestore.serviceImpl;

import org.astashonok.onlinestore.util.ClassName;
import org.astashonok.onlinestorebackend.dao.ProductDAO;
import org.astashonok.onlinestorebackend.dto.Product;
import org.astashonok.onlinestorebackend.exceptions.basicexception.BackendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class HomeService {
    private static final int PRODUCT_ITEMS = 6;
    private List<Product> products;
    private static final Logger logger = LoggerFactory.getLogger(ClassName.getCurrentClassName());

    @Autowired
    private ProductDAO productDAO;

    private void fillInProductsList(){
        this.products = new ArrayList<>();
        try {
            Random random = new Random();
            int productCount = productDAO.getCountProducts();
            for(int i = 0; i < PRODUCT_ITEMS; i++){
                products.add(productDAO.getById(random.nextInt(productCount) + 1));
            }
        } catch (BackendException e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            logger.error(stringWriter.toString());
        }
    }

    public List<Product> getProducts() {
        fillInProductsList();
        return products;
    }
}
