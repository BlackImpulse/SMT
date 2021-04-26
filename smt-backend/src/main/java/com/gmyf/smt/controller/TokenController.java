package com.gmyf.smt.controller;

import com.gmyf.smt.factory.ServiceApiFactory;
import com.gmyf.smt.payload.TokenPayload;
import com.gmyf.smt.service.api.TokenService;
import com.gmyf.smt.service.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam long userId, @RequestParam long serviceId) {
        TokenPayload tokenPayload = new TokenPayload();
        TokenDto tokenDto = tokenService.getTokenByUserIdAndServiceId(userId, serviceId);

        if (tokenDto != null) {
            tokenPayload.setToken(tokenDto);
        } else {
            String url = ServiceApiFactory.getServiceApi(serviceId).getAuthUrl();
            tokenPayload.setUrl(url);
        }

        return ResponseEntity.ok().body(tokenPayload);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TokenDto tokenDto) {
        tokenService.saveOrUpdate(tokenDto);
        return ResponseEntity.ok().build();
    }
}
