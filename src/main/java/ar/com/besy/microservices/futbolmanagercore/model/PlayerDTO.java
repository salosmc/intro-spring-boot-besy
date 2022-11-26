package ar.com.besy.microservices.futbolmanagercore.model;

import lombok.Data;
import lombok.NonNull;

@Data //nos ahorramos casi todas la etiquetas anteriores que usamos en TeamDTO

public class PlayerDTO {

    @NonNull
    private Integer id;

    @NonNull
    private String name;

    private String lastName;

    private Integer number;

    private Integer gol;
}
