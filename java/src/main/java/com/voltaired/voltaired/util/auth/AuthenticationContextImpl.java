package com.voltaired.voltaired.util.auth;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class AuthenticationContextImpl implements AuthenticationContext {
    private User user;

    @Override
    public User getCurrentUser() {
        return user;
    }

    public void setCurrentUser(User user) {
        this.user = user;
    }
}

