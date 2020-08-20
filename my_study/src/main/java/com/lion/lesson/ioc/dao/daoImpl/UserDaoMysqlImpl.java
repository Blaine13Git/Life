package com.lion.lesson.ioc.dao.daoImpl;

import com.muyi.bigMonster.lilianTest.ioc.dao.UserDao;

public class UserDaoMysqlImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("mySql Dao");
    }
}
