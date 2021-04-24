package com.gmyf.smt.service.api;

import javax.persistence.NoResultException;
import java.util.List;

public interface GenericService<T, Dto> {
    Dto get(long id) throws NoResultException;
    void delete(long id);
    void saveOrUpdate(Dto dto);
    List<Dto> list() throws NoResultException;
}
