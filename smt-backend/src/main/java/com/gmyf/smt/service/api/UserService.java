package com.gmyf.smt.service.api;

import com.gmyf.smt.model.entity.User;
import com.gmyf.smt.service.dto.UserDto;

public interface UserService extends GenericService<User, UserDto> {
    boolean existsByUsername(String username);
    UserDto getByUsername(String username);
}
