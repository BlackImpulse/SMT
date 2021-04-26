package com.gmyf.smt.service.impl;

import com.gmyf.smt.dao.api.TokenDao;
import com.gmyf.smt.model.entity.Token;
import com.gmyf.smt.service.AbstractService;
import com.gmyf.smt.service.api.TokenService;
import com.gmyf.smt.service.dto.TokenDto;
import com.gmyf.smt.service.dtoconverter.TokenDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class TokenServiceImpl extends AbstractService<Token, TokenDto> implements TokenService {
    private final TokenDao dao;
    private final TokenDtoConverter converter;

    @Autowired
    public TokenServiceImpl(TokenDao dao, TokenDtoConverter converter) {
        super(dao, converter);
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public TokenDto getTokenByUserIdAndServiceId(long userId, long serviceId) {
        try {
            return converter.convertToDto(dao.getTokenByUserIdAndServiceId(userId, serviceId));
        } catch (NoResultException exception) {
            return null;
        }
    }
}
