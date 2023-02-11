package org.iesvdm.tutoriales.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
//Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(includeFieldNames=true)
//Lombok

@Entity
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socio")
    private long id;

    //Patrón español: nif | nie | passport  : ^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$ | ^[XYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKE]$ | ^[a-z]{3}[0-9]{6}[a-z]?$
    @Pattern(regexp = "(^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$)|(^[XYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKE]$)|(^[a-z]{3}[0-9]{6}[a-z]?$)", message = "Formato DNI incorrecto.")
    @Column(length = 10)
    private String dni;
    private String nombre;
    private String apellidos;
    @OneToOne(mappedBy = "socio", cascade = CascadeType.ALL)
    private Tarjeta tarjeta;

}
