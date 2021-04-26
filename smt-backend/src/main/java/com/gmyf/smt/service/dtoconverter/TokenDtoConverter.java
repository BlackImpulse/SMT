package com.gmyf.smt.service.dtoconverter;

import com.gmyf.smt.model.entity.Token;
import com.gmyf.smt.service.dto.TokenDto;
import org.springframework.stereotype.Service;

@Service
public class TokenDtoConverter extends AbstractDtoConverter<Token, TokenDto> {
    public TokenDtoConverter() {
        super(Token.class, TokenDto.class);
    }
}
