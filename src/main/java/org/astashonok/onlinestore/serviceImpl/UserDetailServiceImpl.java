package org.astashonok.onlinestore.serviceImpl;

import org.astashonok.onlinestorebackend.dao.UserDAO;
import org.astashonok.onlinestorebackend.dto.User;
import org.astashonok.onlinestorebackend.exceptions.basicexception.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userDAO.getByEmail(email);
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword()
                    , grantedAuthorities);
        } catch (BackendException e) {
            e.printStackTrace();
        }
        throw new UsernameNotFoundException("Email user wasn't found");
    }
}
