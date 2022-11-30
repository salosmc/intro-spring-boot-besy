package ar.com.besy.microservices.futbolmanagercore.entities;

import ar.com.besy.microservices.futbolmanagercore.validators.OnCreate;
import ar.com.besy.microservices.futbolmanagercore.validators.OnUpdate;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "equipo")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NonNull
    private Integer id;

    @NonNull
    @Column(name = "nombre_equipo")
    private String name;

    private int year;

    private String logo;

    private int afiliateQuantity;

    private boolean active;

    private LocalDate fundationDate;

    private String email;

    private String cuit;

    private String title;

}


