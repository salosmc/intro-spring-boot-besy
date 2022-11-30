package ar.com.besy.microservices.futbolmanagercore.services;

import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeamService {
    //public TeamDTO getTeamById(Integer id);

    public Optional<TeamDTO> getTeamById(Integer id);
    public List<TeamDTO> findAllTeams(Pageable pageable);
    public Integer saveTeam(TeamDTO teamDTO);
    public void deleteById(Integer id);


}
