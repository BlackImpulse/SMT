package com.gmyf.smt.controller;

import com.gmyf.smt.service.api.MigrationService;
import com.gmyf.smt.service.api.ServiceService;
import com.gmyf.smt.service.api.TokenService;
import com.gmyf.smt.service.api.UserService;
import com.gmyf.smt.service.dto.ServiceDto;
import com.gmyf.smt.service.dto.TokenDto;
import com.gmyf.smt.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MigrationService migrationService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/get")
    public ResponseEntity<?> getByUsername(@RequestParam String username) {
        return ResponseEntity.ok().body(userService.getByUsername(username));
    }

    @GetMapping("/migrations")
    public ResponseEntity<?> getMigrations(@RequestParam String username) {
        UserDto userDto = userService.getByUsername(username);
        return ResponseEntity.ok().body(migrationService.getMigrationsByUserId(userDto.getId()));
    }

    @GetMapping("/services")
    public ResponseEntity<?> getServices(@RequestParam String username) {
        long userId = userService.getByUsername(username).getId();
        List<TokenDto> tokens = tokenService.getTokensByUserId(userId);
        List<ServiceDto> serviceDtoList = tokens.stream().map(TokenDto::getService).collect(Collectors.toList());
        return ResponseEntity.ok().body(serviceDtoList);
    }
}
