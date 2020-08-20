package com.lion.lesson.ioc.dao.daoImpl;


import com.lion.lesson.ioc.dao.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("Default User");
    }
}
