package ar.com.besy.microservices.futbolmanagercore.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


//es configuration y no otra ya que no hace a la logica del negocio si no la inyeccion de alguna herramienta que tarde o temprano podria cambiarse.
@Configuration
public class ClientConfiguration {

    @Bean //spring guarda esta instancia para llamarlo por todos lados, en @Service no hace falta, ya lo hace.
    public RestTemplate getRestTemplete(){
        return new RestTemplate();
    }

}

