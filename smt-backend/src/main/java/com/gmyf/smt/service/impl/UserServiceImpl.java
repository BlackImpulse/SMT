package com.gmyf.smt.service.impl;

import com.gmyf.smt.dao.api.GenericDao;
import com.gmyf.smt.dao.api.UserDao;
import com.gmyf.smt.model.entity.User;
import com.gmyf.smt.service.AbstractService;
import com.gmyf.smt.service.api.UserService;
import com.gmyf.smt.service.dto.UserDto;
import com.gmyf.smt.service.dtoconverter.AbstractDtoConverter;
import com.gmyf.smt.service.dtoconverter.UserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class UserServiceImpl extends AbstractService<User, UserDto> implements UserService {
    private UserDao userDao;
    private UserDtoConverter userDtoConverter;

    @Autowired
    public UserServiceImpl(GenericDao<User> genericDao, AbstractDtoConverter<User, UserDto> abstractDtoConverter, UserDao userDao, UserDtoConverter userDtoConverter) {
        super(genericDao, abstractDtoConverter);
        this.userDao = userDao;
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public boolean existsByUsername(String username) {
        try {
            UserDto user = userDtoConverter.convertToDto(userDao.getUserByUsername(username));
            return user != null;
        } catch (NoResultException exception) {
            return false;
        }
    }
}
