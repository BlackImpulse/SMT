package com.gmyf.smt.controller;

import com.gmyf.smt.service.api.UserService;
import com.gmyf.smt.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> save(@RequestBody UserDto userDto) {
        userService.saveOrUpdate(userDto);
        return ResponseEntity.ok().body("GOOD");
    }
}
