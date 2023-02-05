package com.oliviawu.springbootmall.service.impl;

import com.oliviawu.springbootmall.dao.UserDao;
import com.oliviawu.springbootmall.dto.UserLoginRequest;
import com.oliviawu.springbootmall.dto.UserRegisterRequest;
import com.oliviawu.springbootmall.modal.User;
import com.oliviawu.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //檢查有沒有被註冊過
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if(user!=null){
            log.warn("The e-mail: {} has been registered.",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);//400
        }

        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        if(user==null){
            log.warn("The e-mail: {} not registered.",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);//400
        }
        if(user.getPassword().equals(userLoginRequest.getPassword())){
            return user;
        }else {
            log.warn("The password is not correct.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);//400
        }


    }


}
