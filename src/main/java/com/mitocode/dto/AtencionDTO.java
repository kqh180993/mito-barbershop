package com.mitocode.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtencionDTO {

    private Integer idAtencion;

    @NotNull
    private ClienteDTO cliente;

    @NotNull
    private BarberoDTO barbero;

    @NotNull
    private LocalDateTime fechaAtencion;

    @NotNull
    private String tipoCorte;

    @NotNull
    private boolean estado;

}
