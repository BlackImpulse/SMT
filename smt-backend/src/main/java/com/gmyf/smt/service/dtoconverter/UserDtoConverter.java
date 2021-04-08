package com.gmyf.smt.service.dtoconverter;

import com.gmyf.smt.model.entity.User;
import com.gmyf.smt.service.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserDtoConverter extends AbstractDtoConverter<User, UserDto>{

    public UserDtoConverter() {
        super(User.class, UserDto.class);
    }
}
