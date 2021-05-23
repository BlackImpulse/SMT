package com.gmyf.smt.service.api;

import com.gmyf.smt.model.entity.Migration;
import com.gmyf.smt.payload.MigrationPayload;
import com.gmyf.smt.service.dto.MigrationDto;

import java.util.List;

public interface MigrationService extends GenericService<Migration, MigrationDto> {
    void saveMigration(MigrationPayload migrationPayload, long userId);
    List<MigrationDto> getMigrationsByUserId(long id);
}
