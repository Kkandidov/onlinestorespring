package org.astashonok.onlinestore.validator;

import org.astashonok.onlinestore.util.ClassName;
import org.astashonok.onlinestorebackend.dto.Product;
import org.astashonok.onlinestorebackend.dto.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.Objects;

@Component
public class ProductValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(ClassName.getCurrentClassName());

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }
    @Override
    public void validate(Object o, Errors errors) {
        Product product = (Product) o;

        // description validation
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description.color", "Field.Required");
        if (product.getDescription().getColor().length() < 3 || product.getDescription().getColor().length() > 50) {
            errors.rejectValue("description.color", "Product.size.color");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description.capacity", "Field.Required");
        if (product.getDescription().getCapacity().length() < 3 || product.getDescription().getCapacity().length() > 50) {
            errors.rejectValue("description.capacity", "Product.size.capacity");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description.battery", "Field.Required");
        if (product.getDescription().getBattery().length() < 3 || product.getDescription().getBattery().length() > 50) {
            errors.rejectValue("description.battery", "Product.size.battery");
        }

        // views validation
        int i = 0;
        for (View view : product.getViews()) {
            if (view.getFile() != null && !Objects.equals(view.getFile().getOriginalFilename(), "") &&
                    !(Objects.equals(view.getFile().getContentType(), "image/jpeg"))) {
                errors.rejectValue("views[" + i + "].file", "View.notImage.file");
            }
            i++;
        }

        // fields of the product validation
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Field.Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitPrice", "Field.Required");
        if (product.getUnitPrice() < 0) {
            errors.rejectValue("unitPrice", "Product.negative.unitPrice");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "Field.Required");
        if (product.getQuantity() < 0) {
            errors.rejectValue("quantity", "Product.negative.quantity");
        }
        if (product.getFile() == null || Objects.equals(product.getFile().getOriginalFilename(), "")) {
            errors.rejectValue("file", "Product.null.file");
            logger.debug(String.valueOf(errors));
            return;
        }
        if (!(Objects.equals(product.getFile().getContentType(), "image/jpeg"))) {
            errors.rejectValue("file", "Product.notImage.file");
        }
        logger.debug(String.valueOf(errors));
    }
}
