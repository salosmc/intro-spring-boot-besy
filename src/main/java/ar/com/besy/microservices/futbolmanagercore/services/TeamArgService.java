package ar.com.besy.microservices.futbolmanagercore.services;

import ar.com.besy.microservices.futbolmanagercore.client.TeamClient;
import ar.com.besy.microservices.futbolmanagercore.entities.TeamEntity;
import ar.com.besy.microservices.futbolmanagercore.mappers.TeamMapper;
import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
//import ar.com.besy.microservices.futbolmanagercore.repositories.TeamRepository;
import ar.com.besy.microservices.futbolmanagercore.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private TeamRepository teamRepository;

    //supongamos que ahora este es el que utiliza un cliente externo apra traer un equipo de futbol
    @Autowired
    private TeamClient teamClient;

    //Traemos el mapper para pasar de los dtos a los mappers
    @Autowired
    private TeamMapper teamMapper;


    public Optional<TeamDTO> getTeamById(Integer id){
        /*
        Optional<TeamDTO> optionalTeamDTO = teamRepository.findById(id);
        //TeamDTO teamDTO = teamMapper.getTeamDto(optionTeamEntity.get());

        //return Optional.ofNullable(teamDTO);//
        return optionalTeamDTO;

        //return new TeamDTO(id,"Boca");
        //return Optional.ofNullable(teamClient.getTeamById(id));

         */
        Optional<TeamEntity> optionTeamEntity = teamRepository.findById(id);
        TeamDTO teamDTO = teamMapper.getTeamDto(optionTeamEntity.get());

        return Optional.ofNullable(teamDTO);// optionalTeam;

    }

    public List<TeamDTO> findAllTeams(Pageable pageable) {
        //List<TeamDTO> teams = teamRepository.findByYearLessThan(1900);
        Page<TeamEntity> pageTeams = teamRepository.findAll(pageable); //consulltamos
        List<TeamEntity> teamsEntities = pageTeams.getContent(); //extraemos el contenido
        List<TeamDTO> teams = teamMapper.getTeamsDtos(teamsEntities); //mapeamos
        return teams;
    }


    public List<TeamDTO> findAllTeams() {
        List<TeamEntity> teamsEntities = teamRepository.findAll(); //buscamos todo
        List<TeamDTO> teamsDtos = teamMapper.getTeamsDtos(teamsEntities); //mapeamos
        return teamsDtos; //retornamos toda la lista
    }

    public Integer saveTeam(TeamDTO teamDTO) {
        TeamEntity teamEntity = teamMapper.getTeamEntity(teamDTO);//mapeamos
        teamEntity = teamRepository.save(teamEntity);//guardamos
        return teamEntity.getId();//retornamos el id
    }

    public void deleteById(Integer id) {
        teamRepository.deleteById(id);
    }

}
