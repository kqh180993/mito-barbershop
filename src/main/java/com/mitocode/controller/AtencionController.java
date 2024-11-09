package com.mitocode.controller;

import com.mitocode.dto.AtencionDTO;
import com.mitocode.dto.GenericResponse;
import com.mitocode.model.Atencion;
import com.mitocode.service.IAtencionService;
import jakarta.validation.Valid;
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
@RequestMapping("/atenciones")
@RequiredArgsConstructor
public class AtencionController {

    private final IAtencionService service;
    @Qualifier("atencionMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponse<AtencionDTO>> getAllAtencions() {
        List<AtencionDTO> list = service.findAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(new GenericResponse<>(200, "success", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<AtencionDTO>> getAtencionById(@PathVariable("id") Integer id) {
        Atencion obj = service.findById(id);

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(convertToDto(obj))));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AtencionDTO dto) {
        Atencion obj = service.save(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdAtencion())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<AtencionDTO>> update(@PathVariable("id") Integer id, @Valid @RequestBody AtencionDTO dto) {
        Atencion obj = service.update(id, convertToEntity(dto));

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(convertToDto(obj))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private AtencionDTO convertToDto(Atencion obj) {
        return modelMapper.map(obj, AtencionDTO.class);
    }

    private Atencion convertToEntity(AtencionDTO dto) {
        return modelMapper.map(dto, Atencion.class);
    }

}
