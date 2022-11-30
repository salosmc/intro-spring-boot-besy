package ar.com.besy.microservices.futbolmanagercore.mappers;

import ar.com.besy.microservices.futbolmanagercore.entities.TeamEntity;
import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


//este mapper se encarga de mapear el dto con el entity
//Importante siempre compilar con maven cuando utilizamos el mapping.

@Mapper(componentModel = "spring")
public interface TeamMapper {

    //@Mapping(source = "teamDTO.edad", target = "year") por ejemplo aca le dice que el campo edad en el dto es year en el entity
    public TeamDTO getTeamDto(TeamEntity teamEntity);

    public TeamEntity getTeamEntity(TeamDTO teamDTO);

    public List<TeamDTO> getTeamsDtos(List<TeamEntity> teamsEntity);

    public List<TeamEntity> getTeamsEntities(List<TeamDTO> teamDtos);

}
