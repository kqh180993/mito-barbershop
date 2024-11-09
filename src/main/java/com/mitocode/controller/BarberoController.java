package com.mitocode.controller;

import com.mitocode.dto.BarberoDTO;
import com.mitocode.dto.GenericResponse;
import com.mitocode.model.Barbero;
import com.mitocode.service.IBarberoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/barberos")
@RequiredArgsConstructor
public class BarberoController {

    private final IBarberoService service;
    @Qualifier("barberoMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponse<BarberoDTO>> getAllBarberos() {

        List<BarberoDTO> list = service.findAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(new GenericResponse<>(200, "success", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<BarberoDTO>> getBarberoById(@PathVariable("id") Integer id) {
        Barbero obj = service.findById(id);

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(convertToDto(obj))));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BarberoDTO dto) {
        Barbero obj = service.save(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdBarbero())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<BarberoDTO>> update(@PathVariable("id") Integer id, @RequestBody BarberoDTO dto) {
        Barbero obj = service.update(id, convertToEntity(dto));

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(convertToDto(obj))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findBy/nombre")
    public ResponseEntity<GenericResponse<BarberoDTO>> findByName(@RequestParam("nombre") String nombre) {
        List<BarberoDTO> list = service.findByNombres(nombre).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(new GenericResponse<>(200, "success", list));
    }

    @GetMapping("/findBy/nombre/like")
    public ResponseEntity<GenericResponse<BarberoDTO>> findByNameLike(@RequestParam("nombre") String nombre) {
        List<BarberoDTO> list = service.findByNombresLike(nombre).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(new GenericResponse<>(200, "success", list));
    }

    private BarberoDTO convertToDto(Barbero obj) {
        return modelMapper.map(obj, BarberoDTO.class);
    }

    private Barbero convertToEntity(BarberoDTO dto) {
        return modelMapper.map(dto, Barbero.class);
    }

}
