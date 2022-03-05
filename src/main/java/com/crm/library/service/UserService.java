package com.crm.library.service;

import com.crm.library.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    List<User> findAllByActive();

    User findById(int id);

    void save(User user);

    void delete(int id);

}
