package com.mitocode.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarberoDTO {

    private Integer idBarbero;

    @NotNull
    @Size(min = 3, max = 50, message = "El nombre del cliente debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotNull
    @Size(min = 3, max = 50, message = "El apellido del cliente debe tener entre 3 y 50 caracteres")
    private String apellido;

    @NotNull
    private Integer aniosExperiencia;

    @NotNull
    private boolean estado;

}
