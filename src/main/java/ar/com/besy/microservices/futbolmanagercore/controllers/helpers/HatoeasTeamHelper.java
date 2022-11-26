package ar.com.besy.microservices.futbolmanagercore.controllers.helpers;

import ar.com.besy.microservices.futbolmanagercore.controllers.TeamController;
import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
import lombok.Data;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//es un helper exclusivo para Team que me va a permitir ... agregar algo al equipo, supongo que seran valores del properties. nose
@Component //etiqueta componente permite que este disponible en el scope de spring
@Data
public class HatoeasTeamHelper {

    public void generateSelfLink(TeamDTO teamDTO){

        Link withSelfRel = linkTo(methodOn(TeamController.class).getTeamById(teamDTO.getId())).withSelfRel();
        teamDTO.add(withSelfRel);
    }

    public CollectionModel<TeamDTO> generateLinksSelfList(List<TeamDTO> teams) {

        Link link = linkTo(methodOn(TeamController.class).getAllTeams()).withSelfRel();
        CollectionModel<TeamDTO> result = CollectionModel.of(teams, link);
        return result;
    }

    public void generatePlayersLink(TeamDTO teamDTO) {

        //Creo un link
        Link accountsRel = linkTo(methodOn(TeamController.class).getTeamPlayers(teamDTO.getId())).withRel("players");
        teamDTO.add(accountsRel);

    }

}
