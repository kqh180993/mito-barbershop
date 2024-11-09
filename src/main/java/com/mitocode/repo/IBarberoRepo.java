package com.mitocode.repo;

import com.mitocode.model.Barbero;

import java.util.List;

public interface IBarberoRepo extends IGenericRepo<Barbero, Integer> {

    List<Barbero> findByNombres(String nombres);
    List<Barbero> findByNombresLike(String nombres);

}
