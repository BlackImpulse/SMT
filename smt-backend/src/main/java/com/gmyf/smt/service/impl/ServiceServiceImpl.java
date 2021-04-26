package com.gmyf.smt.service.impl;

import com.gmyf.smt.dao.api.ServiceDao;
import com.gmyf.smt.service.AbstractService;
import com.gmyf.smt.service.api.ServiceService;
import com.gmyf.smt.service.dto.ServiceDto;
import com.gmyf.smt.service.dtoconverter.ServiceDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class ServiceServiceImpl extends AbstractService<com.gmyf.smt.model.entity.Service, ServiceDto> implements ServiceService {
    private final ServiceDao dao;
    private final ServiceDtoConverter converter;

    @Autowired
    public ServiceServiceImpl(ServiceDao dao, ServiceDtoConverter converter) {
        super(dao, converter);
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public ServiceDto getServiceByName(String name) {
        try {
            return converter.convertToDto(dao.getServiceByName(name));
        } catch (NoResultException exception) {
            return null;
        }
    }
}
