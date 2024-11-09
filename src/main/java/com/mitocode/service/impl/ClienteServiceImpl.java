package com.mitocode.service.impl;

import com.mitocode.model.Cliente;
import com.mitocode.repo.IClienteRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteServiceImpl extends CRUDImpl<Cliente, Integer> implements IClienteService {

    private final IClienteRepo repo;

    @Override
    protected IGenericRepo<Cliente, Integer> getRepo() {
        return repo;
    }
}
