package org.astashonok.onlinestore.validator;

import org.astashonok.onlinestore.model.UserAutoModel;
import org.astashonok.onlinestore.service.UserAutoModelService;
import org.astashonok.onlinestorebackend.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserAutoModelValidator implements Validator {

    @Autowired
    private UserAutoModelService userAutoModelService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserAutoModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserAutoModel userAutoModel = (UserAutoModel) o;
        User user = userAutoModel.getUser();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.firstName", "Field.Required");
        validateEmail(user, errors);
        validatePassword(user, errors);
        validateContactNumber(user, errors);
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("user.confirmPassword", "User.different.password");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billing.lineOne", "Field.Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billing.city", "Field.Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billing.state", "Field.Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billing.country", "Field.Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billing.postalCode", "Field.Required");
    }

    private void validateEmail(User user, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.email", "Field.Required");
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        if (user.getEmail() != null) {
            Matcher matcher = pattern.matcher(user.getEmail());
            if (!matcher.matches()) {
                errors.rejectValue("user.email", "User.incorrect.email");
            }
            if (userAutoModelService.findByEmail(user.getEmail()) != null) {
                errors.rejectValue("user.email", "User.duplicate.email");
            }
        }
    }

    private void validatePassword(User user, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.password", "Field.Required");
        /*We want our passwords have :  1) Be between 8 and 40 characters long
                                        2) Contain at least one digit
                                        3) Contain at least one lower case character
                                        4) Contain at least one upper case character
                                        5) Contain at least on special character from [ @ # $ % ! . ]*/
        String regex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
        Pattern pattern = Pattern.compile(regex);
        if (user.getPassword() != null) {
            Matcher matcher = pattern.matcher(user.getPassword());
            if (!matcher.matches()) {
                errors.rejectValue("user.password", "User.incorrect.password");
            }
        }
    }

    private void validateContactNumber(User user, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.contactNumber", "Field.Required");
        String regex = "\\+?\\d{12}|\\+?\\d{3}-?\\d{2}-?\\d{3}-?\\d{2}-?\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        if (user.getContactNumber() != null) {
            Matcher matcher = pattern.matcher(user.getContactNumber());
            if (!matcher.matches()) {
                errors.rejectValue("user.contactNumber", "User.incorrect.contact.number");
            }
        }
    }
}
