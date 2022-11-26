package ar.com.besy.microservices.futbolmanagercore.services;

import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

//CREAMOS OTRA CLASE QUE IMPLEMENTA TeamService PARA VER COMO RESOLVEMOS LA AMBIGUEDAD
//podemos definir prioridades en las etiquetas de spring

//@Primary //primera solucion de spring a la ambiguedad es dar prioridades
//otra solucion es por calificaion, es un tag que definimos en cada clase y descriminamos desde el controller
//@Qualifier("italia")
//otra manera de resolver la ambiguedad es que segun el properties decida que bean levantar.
@ConditionalOnProperty(prefix="app", name="edition", havingValue = "italia")

@Service
public class TeamItalService implements TeamService {

    //    public TeamDTO getTeamById(Integer id)
    public Optional<TeamDTO> getTeamById(Integer id){
        return Optional.ofNullable(new TeamDTO(id,"Milan"));
    }
}
