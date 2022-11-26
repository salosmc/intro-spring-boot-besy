package ar.com.besy.microservices.futbolmanagercore.client;

//supongamos que nuestro cliente ahora esta en la nuve, en otro servidor , lo que sea , un servicio de afuera.

import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
import org.springframework.stereotype.Service;

public interface TeamClient {
    TeamDTO getTeamById(Integer id);
}
