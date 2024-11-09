package com.mitocode.service;

import com.mitocode.model.Barbero;

import java.util.List;

public interface IBarberoService extends ICRUD<Barbero, Integer> {

    List<Barbero> findByNombres(String nombres);
    List<Barbero> findByNombresLike(String nombres);

}
