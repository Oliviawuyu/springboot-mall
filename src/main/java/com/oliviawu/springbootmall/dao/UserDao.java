package com.oliviawu.springbootmall.dao;

import com.oliviawu.springbootmall.dto.UserRegisterRequest;
import com.oliviawu.springbootmall.modal.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

}
