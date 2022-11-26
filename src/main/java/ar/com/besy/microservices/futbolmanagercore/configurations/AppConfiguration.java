package ar.com.besy.microservices.futbolmanagercore.configurations;

/*
#Información de la aplicación
app.name=Futbol Manager 2020 Besy
app.edition=argentina
app.year=2020
app.countries=ar,br,it,es,fr,ca
app.languages=it,es,en
*/

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration // esta etiqueta hace que spring priorice esta clases desde el arranque y permite recuperarlos en el resto del codigo con @Autowired
@Data

//dataso. si los atributos tienen el mismo nombre que en el properties y no cambia el prefijo, puedo definirlo aca y me permite quitarlos value
@ConfigurationProperties(prefix = "app")

// siempre va al application y si no encuentra el valor va a este segundo properties
@PropertySource(value="classpath:messages.properties")

public class AppConfiguration {

    //@Value("${app.name}") //aca le aclaramos que va a tomar el valor del application.properties
    private String name;

    //@Value("${app.edition}")
    private String edition;

    //@Value("${app.year}")
    private Integer year;

    //@Value("${app.countries}")
    private String[] countries;

    @Value("${JAVA_HOME}") // variables de entorno de JAVA // tuve que configurar una variable de entorno no la tenia en mi IntelliJ
    private String javaHome;

    //veamos de donde toma datos, del apllication o del messages //al application le da prioridad
    private List<String> languages;

}
