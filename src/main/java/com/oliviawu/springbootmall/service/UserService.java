package com.oliviawu.springbootmall.service;

import com.oliviawu.springbootmall.dto.UserLoginRequest;
import com.oliviawu.springbootmall.dto.UserRegisterRequest;
import com.oliviawu.springbootmall.modal.Product;
import com.oliviawu.springbootmall.modal.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer id);

    User login(UserLoginRequest userLoginRequest);

}
