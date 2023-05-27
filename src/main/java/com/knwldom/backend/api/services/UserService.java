package com.knwldom.backend.api.services;

import com.knwldom.backend.api.controller.dto.UserDto;
import com.knwldom.backend.api.controller.exceptions.Api500Exception;
import com.knwldom.backend.api.model.User;
import com.knwldom.backend.api.repository.UserDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDto> getAllUsers() {
        try {
            List<User> userList = userDao.getAllUsers();
            return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Api500Exception("Error occurred while fetching users");
        }
    }

    public void createUser(UserDto userDto) {
        try {
            User user = new User();
            user.setDisplayName(userDto.getDisplayName());
            user.setUserId(userDto.getUserId());
            userDao.createUser(user);
        } catch (Exception e) {
            throw new Api500Exception("Error occurred while creating a user");
        }
    }
}
