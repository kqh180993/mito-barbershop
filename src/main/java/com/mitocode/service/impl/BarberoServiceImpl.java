package com.mitocode.service.impl;

import com.mitocode.model.Barbero;
import com.mitocode.repo.IBarberoRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IBarberoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class BarberoServiceImpl extends CRUDImpl<Barbero, Integer> implements IBarberoService {

    private final IBarberoRepo repo;

    @Override
    protected IGenericRepo<Barbero, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Barbero> findByNombres(String nombres) {
        return repo.findByNombres(nombres);
    }

    @Override
    public List<Barbero> findByNombresLike(String nombres) {
        return repo.findByNombresLike("%" + nombres + "%");
    }
}
