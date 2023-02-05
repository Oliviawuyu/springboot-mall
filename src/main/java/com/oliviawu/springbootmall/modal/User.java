package com.oliviawu.springbootmall.modal;

import java.util.Date;

public class User {

    private Integer UserId;
    private String email;
    private String password;
    private Date createdDate;
    private Date lastModifiedDate;

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

//    user_id            INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
//    email              VARCHAR(256) NOT NULL UNIQUE KEY,
//    password           VARCHAR(256) NOT NULL,
//    created_date       TIMESTAMP    NOT NULL,
//    last_modified_date TIMESTAMP    NOT NULL
}
