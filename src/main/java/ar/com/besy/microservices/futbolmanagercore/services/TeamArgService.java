package ar.com.besy.microservices.futbolmanagercore.services;

import ar.com.besy.microservices.futbolmanagercore.client.TeamClient;
import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
//import ar.com.besy.microservices.futbolmanagercore.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//etiqueta de calificacion para resolver ambiguedad
//@Qualifier("arg")

//va a ir al properties y segun app.edition va a decidir que bean leavantar.
@ConditionalOnProperty(prefix="app", name="edition", havingValue = "argentina")
@Lazy // cuando es un servicio no muy frecuentemenete usado, lo que hace es esperar a consumir ese endpoint desde el controller hasta hacer la instancia de la clase.
@Service
public class TeamArgService implements TeamService {

    //ahora nos conectamos al repository
    //@Autowired
    //private TeamRepository teamRepository;

    //supongamos que ahora este es el que utiliza un cliente externo apra traer un equipo de futbol
    @Autowired
    private TeamClient teamClient;
    public Optional<TeamDTO> getTeamById(Integer id){

        //return new TeamDTO(id,"Boca");
        return Optional.ofNullable(teamClient.getTeamById(id));
    }

    public List<TeamDTO> findAllTeams() {
        //List<TeamDTO> teams = teamRepository.findAll();
        //return teams;
        return null;
    }
    public Integer saveTeam(TeamDTO teamDTO) {
        //TeamDTO teamDTO1= teamRepository.save(teamDTO);
        //return teamDTO1.getId();
        return null;
    }

    public void deleteById(Integer id) {

        //teamRepository.deleteById(id);

    }

}
