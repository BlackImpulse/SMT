package com.gmyf.smt.service.api;

import com.gmyf.smt.model.entity.Token;
import com.gmyf.smt.payload.MigrationPayload;
import com.gmyf.smt.service.dto.ServiceDto;
import com.gmyf.smt.service.dto.TokenDto;
import com.gmyf.smt.service.dto.UserDto;

import java.util.List;

public interface TokenService extends GenericService<Token, TokenDto> {
    TokenDto getTokenByUserIdAndServiceId(long userId, long serviceId);
    TokenDto getFreshToken(long userId, long serviceId);
    List<TokenDto> getTokensByUserId(long userId);
}
