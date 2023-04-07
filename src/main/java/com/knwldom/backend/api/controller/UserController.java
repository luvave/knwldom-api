package com.knwldom.backend.api.controller;

import com.knwldom.backend.api.controller.dto.UserDto;
import com.knwldom.backend.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity<List<UserDto>> getUsers() {

        List<UserDto> userList = userService.getAllUsers();

        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserDto>>(userList, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {

        UserDto user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);

    }

    @GetMapping(value = "/me")
    public ResponseEntity<?> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof DefaultOidcUser) {
                return ResponseEntity.ok(((DefaultOidcUser) principal).getUserInfo().toString());
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
