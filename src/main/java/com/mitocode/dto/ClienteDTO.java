package com.mitocode.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Integer idCliente;

    @NotNull
    @Size(min = 3, max = 50, message = "El nombre del cliente debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotNull
    @Size(min = 3, max = 50, message = "El apellido del cliente debe tener entre 3 y 50 caracteres")
    private String apellido;

    @NotNull
    @Size(min = 3, max = 50, message = "El dni del cliente debe tener entre 3 y 50 caracteres")
    private String dni;

    @NotNull
    private Integer edad;

}
