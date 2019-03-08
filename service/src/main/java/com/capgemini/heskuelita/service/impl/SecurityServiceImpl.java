package com.capgemini.heskuelita.service.impl;

import com.capgemini.heskuelita.core.Beans.User;
import com.capgemini.heskuelita.service.ISecurityService;
import com.capgemini.heskuelita.service.SecurityException;
import com.capgemini.heskuelita.data.IUserDao;


public class SecurityServiceImpl implements ISecurityService {


    private IUserDao userDao;
    public SecurityServiceImpl(IUserDao userDao){
        super();
        this.userDao=userDao;
    }




    @Override
    public void login(User user) throws SecurityException {

        User us= this.userDao.login(user.getUserName(), usergetPassword(0));
        }catch(Exception e){

        throw new SecurityException(e);
    }
    }
}
