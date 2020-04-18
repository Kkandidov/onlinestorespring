package org.astashonok.onlinestore.service;

import org.astashonok.onlinestore.model.UserAutoModel;
import org.astashonok.onlinestorebackend.dto.User;

public interface UserAutoModelService {

    void save(UserAutoModel userAutoModel);

    User findByEmail(String email);
}
