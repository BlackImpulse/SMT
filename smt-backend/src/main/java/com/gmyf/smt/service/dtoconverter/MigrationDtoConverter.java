package com.gmyf.smt.service.dtoconverter;

import com.gmyf.smt.model.entity.Migration;
import com.gmyf.smt.service.dto.MigrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MigrationDtoConverter extends AbstractDtoConverter<Migration, MigrationDto> {
    private final MigrationTypeDtoConverter migrationTypeDtoConverter;

    @Autowired
    public MigrationDtoConverter(MigrationTypeDtoConverter migrationTypeDtoConverter) {
        super(Migration.class, MigrationDto.class);
        this.migrationTypeDtoConverter = migrationTypeDtoConverter;
    }

    @Override
    public MigrationDto convertToDto(Migration entity) {
        MigrationDto result = super.convertToDto(entity);
        result.setMigrationTypeDto(migrationTypeDtoConverter.convertToDto(entity.getMigrationType()));
        return result;
    }
}
