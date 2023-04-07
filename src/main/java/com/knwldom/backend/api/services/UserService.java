package com.knwldom.backend.api.services;

import com.knwldom.backend.api.controller.dto.UserDto;
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
        List<User> userList = userDao.getAllUsers();

        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    public UserDto getUserById(Integer id) {
        List<User> userList = userDao.getUserById(id);

        if (userList.isEmpty()) {
            return null;
        }

        return modelMapper.map(userList.get(0), UserDto.class);
    }
}
