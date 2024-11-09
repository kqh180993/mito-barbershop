package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAtencion;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(name = "FK_atencion_cliente"))
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "id_barbero", nullable = false, foreignKey = @ForeignKey(name = "FK_atencion_barbero"))
    private Barbero idBarbero;

    @Column(nullable = false)
    private LocalDateTime fechaAtencion;

    @Column(nullable = false, length = 50)
    private String tipoCorte;

    @Column(nullable = false)
    private boolean estado;

}
