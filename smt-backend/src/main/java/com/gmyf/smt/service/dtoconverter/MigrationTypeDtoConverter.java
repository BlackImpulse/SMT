package com.gmyf.smt.service.dtoconverter;

import com.gmyf.smt.model.entity.MigrationType;
import com.gmyf.smt.service.dto.MigrationTypeDto;
import org.springframework.stereotype.Service;

@Service
public class MigrationTypeDtoConverter extends AbstractDtoConverter<MigrationType, MigrationTypeDto> {
    public MigrationTypeDtoConverter() {
        super(MigrationType.class, MigrationTypeDto.class);
    }
}
