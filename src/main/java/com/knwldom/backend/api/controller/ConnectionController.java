package com.knwldom.backend.api.controller;

import com.knwldom.backend.api.model.User;
import com.knwldom.backend.api.repository.ConnectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConnectionController {

    @Autowired
    ConnectionDao connectionDao;

    @RequestMapping(value = "/add", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<User> addAConnectionToUser(@RequestParam Integer id, @RequestParam String connection) {

        connectionDao.addAConnection(id, connection);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<User> removeAConnectionToUser(@RequestParam Integer id, @RequestParam String connection) {

        connectionDao.deleteAConnection(id, connection);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
