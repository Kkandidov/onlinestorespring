package org.astashonok.onlinestore.service;

public interface SecurityService {

    String findLoggedInEmail();

    void autoLogIn(String email, String password);
}
