package org.astashonok.onlinestore.model;

import org.astashonok.onlinestorebackend.dto.Address;
import org.astashonok.onlinestorebackend.dto.User;

public class UserAutoModel {
    private User user;
    private Address billing;

    public UserAutoModel() {
    }

    public UserAutoModel(User user, Address billing) {
        this.user = user;
        this.billing = billing;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }
}