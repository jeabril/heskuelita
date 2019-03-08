package com.capgemini.heskuelita.service;

import com.capgemini.heskuelita.core.Beans.User;

public interface ISecurityService {

    void login(User user) throws SecurityException;
}
