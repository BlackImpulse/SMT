package com.gmyf.smt.service.dtoconverter;

import com.gmyf.smt.service.dto.ServiceDto;
import org.springframework.stereotype.Service;

@Service
public class ServiceDtoConverter extends AbstractDtoConverter<com.gmyf.smt.model.entity.Service, ServiceDto> {
    public ServiceDtoConverter() {
        super(com.gmyf.smt.model.entity.Service.class, ServiceDto.class);
    }
}
