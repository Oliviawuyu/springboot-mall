package com.oliviawu.springbootmall.service.impl;

import com.oliviawu.springbootmall.dao.UserDao;
import com.oliviawu.springbootmall.dto.UserRegisterRequest;
import com.oliviawu.springbootmall.modal.User;
import com.oliviawu.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }


}
