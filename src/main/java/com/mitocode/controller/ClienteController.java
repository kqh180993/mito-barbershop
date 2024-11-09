package com.mitocode.controller;

import com.mitocode.dto.ClienteDTO;
import com.mitocode.dto.GenericResponse;
import com.mitocode.model.Cliente;
import com.mitocode.service.IClienteService;
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
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteService service;
    @Qualifier("clienteMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponse<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> list = service.findAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(new GenericResponse<>(200, "success", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ClienteDTO>> getClienteById(@PathVariable("id") Integer id) {
        Cliente obj = service.findById(id);

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(convertToDto(obj))));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ClienteDTO dto) {
        Cliente obj = service.save(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdCliente())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<ClienteDTO>> update(@PathVariable("id") Integer id, @Valid @RequestBody ClienteDTO dto) {
        Cliente obj = service.update(id, convertToEntity(dto));

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(convertToDto(obj))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private ClienteDTO convertToDto(Cliente obj) {
        return modelMapper.map(obj, ClienteDTO.class);
    }

    private Cliente convertToEntity(ClienteDTO dto) {
        return modelMapper.map(dto, Cliente.class);
    }

}
