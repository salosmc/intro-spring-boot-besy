package ar.com.besy.microservices.futbolmanagercore.controllers.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
//un DTO de errores jaja
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;

}
