package org.astashonok.onlinestore.serviceImpl;

import org.astashonok.onlinestore.model.UserAutoModel;
import org.astashonok.onlinestore.service.UserAutoModelService;
import org.astashonok.onlinestore.util.ClassName;
import org.astashonok.onlinestorebackend.dao.AddressDAO;
import org.astashonok.onlinestorebackend.dao.RoleDAO;
import org.astashonok.onlinestorebackend.dao.UserDAO;
import org.astashonok.onlinestorebackend.dto.Address;
import org.astashonok.onlinestorebackend.dto.User;
import org.astashonok.onlinestorebackend.exceptions.basicexception.BackendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

@Service
public class UserAutoModelServiceImpl implements UserAutoModelService {

    private static final Logger logger = LoggerFactory.getLogger(ClassName.getCurrentClassName());

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private AddressDAO addressDAO;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UserAutoModel userAutoModel) {
        try {
            User user = userAutoModel.getUser();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRole(roleDAO.getById(2));
            user.setEnabled(true);
            Address address = userAutoModel.getBilling();
            address.setBilling(true);
            address.setShipping(false);
            address.setUser(user);
            userDAO.add(user);
            addressDAO.add(address);
        } catch (BackendException e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            logger.error(stringWriter.toString());
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return userDAO.getByEmail(email);
        } catch (BackendException e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            logger.error(stringWriter.toString());
        }
        return null;
    }
}
