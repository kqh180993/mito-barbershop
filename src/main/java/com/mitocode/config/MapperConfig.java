package com.mitocode.config;

import com.mitocode.dto.AtencionDTO;
import com.mitocode.dto.BarberoDTO;
import com.mitocode.dto.ClienteDTO;
import com.mitocode.model.Atencion;
import com.mitocode.model.Barbero;
import com.mitocode.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean(name = "defaultMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "clienteMapper")
    public ModelMapper clienteMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(Cliente.class, ClienteDTO.class)
                .addMapping(Cliente::getNombres, (dest, v) -> dest.setNombre((String) v))
                .addMapping(Cliente::getApellidos, (dest, v) -> dest.setApellido((String) v));

        mapper.createTypeMap(ClienteDTO.class, Cliente.class)
                .addMapping(ClienteDTO::getNombre, (dest, v) -> dest.setNombres((String) v))
                .addMapping(ClienteDTO::getApellido, (dest, v) -> dest.setApellidos((String) v));

        return mapper;
    }

    @Bean(name = "barberoMapper")
    public ModelMapper barberoMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(Barbero.class, BarberoDTO.class)
                .addMapping(Barbero::getNombres, (dest, v) -> dest.setNombre((String) v))
                .addMapping(Barbero::getApellidos, (dest, v) -> dest.setApellido((String) v));

        mapper.createTypeMap(BarberoDTO.class, Barbero.class)
                .addMapping(BarberoDTO::getNombre, (dest, v) -> dest.setNombres((String) v))
                .addMapping(BarberoDTO::getApellido, (dest, v) -> dest.setApellidos((String) v));

        return mapper;
    }

    @Bean(name = "atencionMapper")
    public ModelMapper atencionMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(Atencion.class, AtencionDTO.class)
                .addMapping(e -> e.getIdCliente().getNombres(), (dest, v) -> dest.getCliente().setNombre((String) v))
                .addMapping(e -> e.getIdCliente().getApellidos(), (dest, v) -> dest.getCliente().setApellido((String) v))
                .addMapping(e -> e.getIdBarbero().getNombres(), (dest, v) -> dest.getBarbero().setNombre((String) v))
                .addMapping(e -> e.getIdBarbero().getApellidos(), (dest, v) -> dest.getBarbero().setApellido((String) v));

        return mapper;
    }

}
