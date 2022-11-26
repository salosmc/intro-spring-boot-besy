package ar.com.besy.microservices.futbolmanagercore.services;

import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TeamService {
    //public TeamDTO getTeamById(Integer id);

    public Optional<TeamDTO> getTeamById(Integer id);

}
