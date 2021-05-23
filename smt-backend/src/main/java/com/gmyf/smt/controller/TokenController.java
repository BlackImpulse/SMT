package com.gmyf.smt.controller;

import com.gmyf.smt.factory.ServiceApiFactory;
import com.gmyf.smt.factory.api.ServiceApi;
import com.gmyf.smt.payload.TokenPayload;
import com.gmyf.smt.service.api.TokenService;
import com.gmyf.smt.service.api.UserService;
import com.gmyf.smt.service.dto.ServiceDto;
import com.gmyf.smt.service.dto.TokenDto;
import com.gmyf.smt.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam long userId, @RequestParam long serviceId) {
        TokenPayload tokenPayload = new TokenPayload();
        TokenDto tokenDto = tokenService.getFreshToken(userId, serviceId);
        ServiceApi serviceApi = ServiceApiFactory.getServiceApi(serviceId);

        if (tokenDto != null) {
            tokenPayload.setToken(tokenDto.getAccessToken());
        } else {
            String url = serviceApi.getAuthUrl();
            tokenPayload.setUrl(url);
        }

        return ResponseEntity.ok().body(tokenPayload);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TokenDto tokenDto, @RequestParam String code) {
        ServiceApi serviceApi = ServiceApiFactory.getServiceApi(tokenDto.getService().getId());
        String[] tokens = serviceApi.getTokens(code);
        tokenDto.setAccessToken(tokens[0]);
        tokenDto.setRefreshToken(tokens[1]);
        tokenDto.setExpiresIn(Integer.parseInt(tokens[2]));
        tokenDto.setCreationTimestamp(Timestamp.from(Instant.now()));
        tokenService.saveOrUpdate(tokenDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{username}/{serviceId}")
    public ResponseEntity<?> delete(@PathVariable("username") String username, @PathVariable("serviceId") long serviceId) {
        long userId = userService.getByUsername(username).getId();
        TokenDto token = tokenService.getTokenByUserIdAndServiceId(userId, serviceId);
        tokenService.delete(token.getId());
        return ResponseEntity.ok().build();
    }
}
