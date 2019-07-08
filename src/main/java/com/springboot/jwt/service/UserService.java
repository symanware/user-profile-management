package com.springboot.jwt.service;


import com.springboot.jwt.model.UpdateUser;
import com.springboot.jwt.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User findByUserById(long id);

    User findUserByName(String userName);

    List<User> findAllUsers();

    User save(User user);

    void delete(long id);

    User updateUser(Long id, User user);

    User partialUpdateUser(Long id, UpdateUser user);
}