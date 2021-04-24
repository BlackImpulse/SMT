package com.gmyf.smt.service;

import com.gmyf.smt.dao.api.GenericDao;
import com.gmyf.smt.service.api.GenericService;
import com.gmyf.smt.service.dtoconverter.AbstractDtoConverter;

import java.util.List;

public abstract class AbstractService<T, Dto> implements GenericService<T, Dto> {
    private GenericDao<T> genericDao;
    private AbstractDtoConverter<T, Dto> abstractDtoConverter;

    public AbstractService(GenericDao<T> genericDao, AbstractDtoConverter<T, Dto> abstractDtoConverter) {
        this.genericDao = genericDao;
        this.abstractDtoConverter = abstractDtoConverter;
    }

    @Override
    public Dto get(long id) {
        return abstractDtoConverter.convertToDto(genericDao.get(id));
    }

    @Override
    public void delete(long id) {
        T entity = genericDao.get(id);
        genericDao.delete(entity);
    }

    @Override
    public void saveOrUpdate(Dto dto) {
        T entity = abstractDtoConverter.convertToEntity(dto);
        genericDao.saveOrUpdate(entity);
    }

    @Override
    public List<Dto> list() {
        return abstractDtoConverter.convertListToDto(genericDao.list());
    }
}
