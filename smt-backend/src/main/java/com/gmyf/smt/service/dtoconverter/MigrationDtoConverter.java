package com.gmyf.smt.service.dtoconverter;

import com.gmyf.smt.model.entity.Migration;
import com.gmyf.smt.service.dto.MigrationDto;
import org.springframework.stereotype.Service;

@Service
public class MigrationDtoConverter extends AbstractDtoConverter<Migration, MigrationDto> {
    public MigrationDtoConverter() {
        super(Migration.class, MigrationDto.class);
    }
}
