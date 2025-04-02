package com.portfolioapi.service;

import com.portfolioapi.dto.UserDto;
import com.portfolioapi.entity.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User save(UserDto userDto);
    List<User> getAll();
    User getById(Long id);
    User update(Long id, UserDto userDto);
    void delete(Long id);
    User findByUsername(String username);


}