package com.lion.lesson.ioc.service.serviceImpl;


import com.lion.lesson.ioc.dao.UserDao;
import com.lion.lesson.ioc.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
