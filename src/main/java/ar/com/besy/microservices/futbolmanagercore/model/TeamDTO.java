package ar.com.besy.microservices.futbolmanagercore.model;

import ar.com.besy.microservices.futbolmanagercore.validators.CUIT;
import ar.com.besy.microservices.futbolmanagercore.validators.OnCreate;
import ar.com.besy.microservices.futbolmanagercore.validators.OnUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDate;

//lombok
//ctrl+F12 -> para ver todos los metodos que crea las etiqueta de lombok

@Getter
@Setter
@NoArgsConstructor //constructor sin argumentos
        (staticName = "of") // sirve para constructor estatico -> of().TeamDTO
@AllArgsConstructor //constructor con todos los argumentos
@RequiredArgsConstructor //constructor a medida con los dos campos @nonNull
@EqualsAndHashCode
@ToString
//@Data

// descripcion para la documentacion de swagger
@ApiModel(description = "Futbol Team")

//Hacemos que extienda de representation Model , lo que va a hacer es implemnetar metodos para agregar links a este objeto

//Creamos la tablas en la base de datos
@Entity
public class TeamDTO extends RepresentationModel<TeamDTO> {

    @NonNull // para que sea necesario para el contructor a medida
    // descripcion y otros de la propiedad de algun atributo
    @ApiModelProperty(notes = "Unique identifier of the team.", example = "1", required = true, position = 0)

    @NotNull // validamos que no sean null
    private Integer id;

    @NonNull
    @NotBlank // no puede tener un string vacio
    private String name;

    @ToString.Exclude //para eliminar algun atributo de la impresión
    //@Min(1700) //validation
    //@Max(2000) //validation
    @PositiveOrZero (message="{app.field.validation.error.year}") //validation //le especificamos, si no pasa esa validacion tome este mensaje como error
    private int year;

    @Size(min=4,max=20) //validation
    private String logo;

    private int afiliateQuantity;

    //@AssertTrue // validation, si o si tiene que ser true
    //puede suceder que este campo quiera validarse distinto, segun desde el endpoint que se ataque.
    @AssertTrue(groups = OnUpdate.class) //cuando se ejecute desde update, validamos que sea verdadero
    @AssertFalse(groups = OnCreate.class) //cuando se ejecutra desde create, validamos que sea falso

    private boolean active;

    @Past // validation, nos aseguramos que la fecha sea pasado
    private LocalDate fundationDate;

    @Email // validation
    private String email;

    @CUIT // validation personalizada, muy crac hacer tus propias etiquetas :D
    private String cuit;

    //Creamos unos atributos para la prueba que hacemos en la capa clente
    private String title;
}
