package com.capgemini.heskuelita.service;

import com.capgemini.heskuelita.core.Beans.UserAnnotation;

public interface ISecurityService {

  
    void login (UserAnnotation user) throws SecurityException;
    void NewUser(UserAnnotation user) throws  SecurityException;
}
