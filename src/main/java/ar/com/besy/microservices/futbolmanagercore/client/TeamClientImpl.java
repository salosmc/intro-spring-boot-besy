package ar.com.besy.microservices.futbolmanagercore.client;

import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeamClientImpl implements TeamClient{
    @Autowired
    private RestTemplate restTemplate;
    public TeamDTO getTeamById(Integer id) {

        //RestTemplate restTemplate = new RestTemplate(); //resttempleate me permite consumir una url externa y traerme el json
        //esta instanciación de restTempleate no estaria bien si vamos con las buenas practicas de spring. creamos un archivo de configuración del restTempleate
        String externalServiceUrl = "https://jsonplaceholder.typicode.com/posts/1";
        TeamDTO team = restTemplate.getForObject(externalServiceUrl, TeamDTO.class); // aca lo que hago es mapear los atributos del json con el objeto.
        return team;

    }
}