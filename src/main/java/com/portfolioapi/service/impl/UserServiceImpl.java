package com.portfolioapi.service.impl;

import com.portfolioapi.dto.UserDto;
import com.portfolioapi.entity.User;
import com.portfolioapi.repository.UserRepository;
import com.portfolioapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User save(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        // BU XƏTT ÇOX VACİBDİR!
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User update(Long id, UserDto userDto) {
        User existing = getById(id);
        existing.setUsername(userDto.getUsername());
        existing.setEmail(userDto.getEmail());
        existing.setPassword(userDto.getPassword());
        return userRepository.save(existing);
    }
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
