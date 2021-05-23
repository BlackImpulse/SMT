package com.gmyf.smt.service.impl;

import com.gmyf.smt.dao.api.MigrationDao;
import com.gmyf.smt.model.entity.Migration;
import com.gmyf.smt.payload.MigrationPayload;
import com.gmyf.smt.service.AbstractService;
import com.gmyf.smt.service.api.MigrationService;
import com.gmyf.smt.service.dto.MigrationDto;
import com.gmyf.smt.service.dto.UserDto;
import com.gmyf.smt.service.dtoconverter.MigrationDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.sql.Date;
import java.util.List;

@Service
public class MigrationServiceImpl extends AbstractService<Migration, MigrationDto> implements MigrationService {
    private final MigrationDao dao;
    private final MigrationDtoConverter converter;

    @Autowired
    public MigrationServiceImpl(MigrationDao dao, MigrationDtoConverter converter) {
        super(dao, converter);
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public void saveMigration(MigrationPayload migrationPayload, long userId) {
        MigrationDto migrationDto = new MigrationDto();
        UserDto userDto = new UserDto();
        userDto.setId(userId);
        migrationDto.setFromService(migrationPayload.getFromService());
        migrationDto.setToService(migrationPayload.getToService());
        migrationDto.setDate(new Date(System.currentTimeMillis()));
        migrationDto.setUser(userDto);
        migrationDto.setItemsCount(migrationPayload.getNames().size());
        migrationDto.setMigrationTypeDto(migrationPayload.getMigrationType());
        dao.saveOrUpdate(converter.convertToEntity(migrationDto));
    }

    @Override
    public List<MigrationDto> getMigrationsByUserId(long id) {
        try {
            return converter.convertListToDto(dao.getMigrationsByUserId(id));
        } catch (NoResultException exception) {
            return null;
        }
    }
}
