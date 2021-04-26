package com.gmyf.smt.service.api;

import com.gmyf.smt.model.entity.Token;
import com.gmyf.smt.service.dto.TokenDto;

public interface TokenService extends GenericService<Token, TokenDto> {
    TokenDto getTokenByUserIdAndServiceId(long userId, long serviceId);
}
