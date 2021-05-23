package com.gmyf.smt.service.impl;

import com.gmyf.smt.dao.api.TokenDao;
import com.gmyf.smt.factory.ServiceApiFactory;
import com.gmyf.smt.factory.api.ServiceApi;
import com.gmyf.smt.model.entity.Token;
import com.gmyf.smt.service.AbstractService;
import com.gmyf.smt.service.api.TokenService;
import com.gmyf.smt.service.dto.TokenDto;
import com.gmyf.smt.service.dtoconverter.TokenDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

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

    @Override
    public TokenDto getFreshToken(long userId, long serviceId) {
        TokenDto tokenDto = getTokenByUserIdAndServiceId(userId, serviceId);

        if (tokenDto != null) {
            Timestamp expiresInTimestamp = new Timestamp(tokenDto.getCreationTimestamp().getTime() + tokenDto.getExpiresIn() * 1000);
            Timestamp timestampNow = Timestamp.from(Instant.now());
            if (expiresInTimestamp.before(timestampNow)) {
                ServiceApi serviceApi = ServiceApiFactory.getServiceApi(serviceId);
                String[] tokens = serviceApi.getNewAccessToken(tokenDto.getRefreshToken());
                tokenDto.setAccessToken(tokens[0]);
                tokenDto.setExpiresIn(Integer.parseInt(tokens[2]));
                tokenDto.setCreationTimestamp(timestampNow);
                dao.saveOrUpdate(converter.convertToEntity(tokenDto));
            }
        }

        return tokenDto;
    }

    @Override
    public List<TokenDto> getTokensByUserId(long userId) {
        try {
            return converter.convertListToDto(dao.getTokensByUserId(userId));
        } catch (NoResultException exception) {
            return null;
        }
    }
}
