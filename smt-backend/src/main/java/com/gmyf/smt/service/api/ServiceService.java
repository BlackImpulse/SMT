package com.gmyf.smt.service.api;

import com.gmyf.smt.model.entity.Service;
import com.gmyf.smt.service.dto.ServiceDto;

public interface ServiceService extends GenericService<Service, ServiceDto> {
    ServiceDto getServiceByName(String name);
}
