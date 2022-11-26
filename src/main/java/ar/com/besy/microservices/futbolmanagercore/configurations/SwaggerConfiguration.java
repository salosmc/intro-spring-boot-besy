package ar.com.besy.microservices.futbolmanagercore.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends NullPointerException{

    @Bean
    public Docket api() {

        //definimos des mensajes personalizados para todos los endpoints
        List<ResponseMessage> responseMessages = Arrays.asList(
                new ResponseMessageBuilder().code(200).message("Respuesta OK si la operacion fue exitosa").build(), //consultar -> mensaje que me devuelve "OK"
                new ResponseMessageBuilder().code(404).message("Respuesta no encontrada si no se pudo encontrar el recurso").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false) //eliminar mensajes por defecto de los endpoint
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .globalResponseMessage(RequestMethod.PUT, responseMessages)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.DELETE, responseMessages)
                .apiInfo(new ApiInfo("Futbol Manager API", "Microservice", "1.0", "http://besysoft.com",
                        new Contact("Rafael Benedettelli", "besysoft.com", "rblbene@gmail.com"),
                        "Uso exclusivo Besy", "http://besysoft.com", Collections.emptyList()));

    }

}
