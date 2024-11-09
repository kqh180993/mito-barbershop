package com.mitocode.service.impl;

import com.mitocode.model.Atencion;
import com.mitocode.repo.IAtencionRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IAtencionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AtencionServiceImpl extends CRUDImpl<Atencion, Integer> implements IAtencionService {

    private final IAtencionRepo repo;

    @Override
    protected IGenericRepo<Atencion, Integer> getRepo() {
        return repo;
    }
}
