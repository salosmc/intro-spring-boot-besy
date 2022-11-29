package ar.com.besy.microservices.futbolmanagercore.controllers;

import ar.com.besy.microservices.futbolmanagercore.configurations.AppConfiguration;
import ar.com.besy.microservices.futbolmanagercore.controllers.helpers.HatoeasTeamHelper;
import ar.com.besy.microservices.futbolmanagercore.model.PlayerDTO;
import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
import ar.com.besy.microservices.futbolmanagercore.services.TeamArgService;
import ar.com.besy.microservices.futbolmanagercore.services.TeamService;
import ar.com.besy.microservices.futbolmanagercore.validators.OnCreate;
import ar.com.besy.microservices.futbolmanagercore.validators.OnUpdate;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j //hace el la linea 37 y lo deja en la variable "log"
@RestController
@RequestMapping("/teams") // unificamos rutas

@Api(tags="API de equipo de futbol") // swagger, descripcion de la api de teams
public class TeamController {

    //private final Logger logger = LoggerFactory.getLogger(TeamController.class); //antes de @SlF4j

    //private AppConfiguration appConfiguration = new AppConfiguration();
    //con @Autowired evitamos instanciarlo nosotros y permite tomar todos los valores definidos en el properties externalizados
    @Autowired
    private AppConfiguration appConfiguration;

    @Autowired
    private HatoeasTeamHelper hatoeasTeamHelper;

    //antes del usar las etiquetas del scope de spring en el @Service y aca @Autowired
    //private TeamArgService teamArgService = new TeamArgService();


    //private TeamArgService teamArgService; //Ahi estaria bien, pero como spring esta orientado a un diseño de interfaces, podemos crear un interface de TeamService que implementa la clase TeamArgService.
    //@Qualifier("italia") //etiqueta para resolver ambiguedad.
    @Autowired
    private TeamService teamService;

    @GetMapping("/saludo/{userId}")
    public String holaMundo(@PathVariable String userId){

        MDC.put("userid", userId); //OBJETO SINGLETON, nos permite guardar variables de contexto.

        log.trace("Ejecutando hola mundo trace");
        log.debug("Ejecutando hola mundo debug");
        log.info("Ejecutando hola mundo info");
        log.warn("Ejecutando hola mundo warn");
        log.error("Ejecutando hola mundo error");

        return "Hola Mundo Spring Rest"+appConfiguration.toString();
    }

    //@GetMapping("/teams/{id}") //antes de unificar rutas
    @GetMapping("/{id}")

    //swagger, documentación del controller del recurso
    @ApiOperation(notes="Consultar un equipo por id", value="Insertar id del equipo")

    //swagger, descripcion de mensajes de error // ya no hace falta por que esta todo en el swaggerConfiguration
    /*
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Responde OK si la operación fue exitosa"),
            @ApiResponse(code = 404, message = "Respuesta no encontrada si no se pudo encontrar el recurso")
    })
    */

    //public TeamDTO getTeamById(@PathVariable Integer id) // antes de ResponseEntity, que permite devolver codigos de errores especificos

    public ResponseEntity<TeamDTO> getTeamById(
            @ApiParam(example="1", value = "Identificador de equipo", allowableValues = "1,2,3,4", required = true) //swagger
            @PathVariable Integer id){

        //TeamDTO teamDTO = new TeamDTO(id,"Boca"); //Ahora traemos a este DTO desde la capa services
        //TeamDTO teamDTO = teamArgService.getTeamById(id); //antes de usar la interfaz
        //TeamDTO teamDTO = teamService.getTeamById(id); //antes del optional

        // simulamos que error debemos devolver si no se encontro el recurso -> notFound 404
        //TeamDTO teamDTO =  null;
        /*
        if(teamDTO == null){
            return ResponseEntity.notFound().build();
        }
        */
        //Hatoeas, aca lo que hacemos es decirle que vamos a crear un link a si mismo
        //siempre es buena practica.
        //hatoeasTeamHelper.generateSelfLink(teamDTO); //antes del optional

        //return ResponseEntity.ok(teamDTO); //explicitamos para la buena practica //antes del opcional

        TeamDTO teamDTO = null;

        try{
            Optional<TeamDTO> optionalTeamDTO = teamService.getTeamById(id);
            teamDTO = optionalTeamDTO.orElseThrow(NoSuchElementException::new);
            hatoeasTeamHelper.generateSelfLink(teamDTO);

        }catch(NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(teamDTO);
    }

    //@GetMapping("/teams")
    @GetMapping
    //antes //public ResponseEntity<List<TeamDTO>>  getAllTeams()
    //ahora para devolver el link del recurso consumido podemos devolver un CollectionModel {links, content}
    public ResponseEntity<CollectionModel<TeamDTO>>  getAllTeams(
            @PageableDefault(size = 3,sort = {"name","year"},direction = Sort.Direction.ASC)
            Pageable pageable){

        List<TeamDTO> teams = new ArrayList<>();
        TeamDTO teamDTO = null;

        try{
            //teams.add(new TeamDTO(1,"Boca"));
            //teams.add(new TeamDTO(2,"River"));
            teams = teamService.findAllTeams(pageable);

        }catch(NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

        //aca ponemos todos los links al recurso de cada equipo
        teams.forEach(team -> {
            hatoeasTeamHelper.generateSelfLink(team); // el propio recurso
            hatoeasTeamHelper.generatePlayersLink(team); // el recurso de los jugadores
        });
        //aca agregamos el link a al recurso consumido.
        CollectionModel<TeamDTO> teamsModel = hatoeasTeamHelper.generateLinksSelfList(teams,pageable);

        return ResponseEntity.ok( teamsModel);
    }

    //@PostMapping ("/teams")
    @PostMapping
    //public ResponseEntity<String> saveTeam(@RequestBody TeamDTO teamDTO){

    //tenemos que aclarar que queremos validar este team de entrada
    //public ResponseEntity<String> saveTeam(@Valid @RequestBody TeamDTO teamDTO)

    //descriminamos que tipo de validacion se hace para el mismo campo
    public ResponseEntity<String> saveTeam(@Validated(OnCreate.class) @RequestBody TeamDTO teamDTO){

        System.out.println("Saving team...."+teamDTO.toString() );

        teamService.saveTeam(teamDTO); //guardamos

        //antes//return ResponseEntity.ok("http://localhost:8080/teams/"+ teamDTO.toString());
        //Aca obtenemos la url actual de este recurso.
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(teamDTO.getId())
                .toUri();

        // return ResponseEntity.ok(location.toString()+teamDTO.toString()); // lo que se me ocurria a mi
        return ResponseEntity.created(location).build();

    }

    //@PutMapping("/teams")
    @PutMapping

    //public ResponseEntity<TeamDTO> updateTeam(@RequestBody TeamDTO teamDTO)

    public ResponseEntity<TeamDTO> updateTeam(@Validated(OnUpdate.class) @RequestBody TeamDTO teamDTO){
        System.out.println("Saving team...."+teamDTO.toString() );

        //antes de actualizarlo, capaz debemos buscarlo ... -> notFound 404
        if(teamDTO == null){
            return ResponseEntity.notFound().build();
        }

        teamService.saveTeam(teamDTO);

        return ResponseEntity.ok(teamDTO);
    }

    //@DeleteMapping("/teams/{id}")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamById(@PathVariable Integer id){
        System.out.println("Eliminando team con id" + id);

        //simulamos que no se encontro ese recurso -> notFound 404
        TeamDTO teamDTO = null;
        if(teamDTO == null){
            return ResponseEntity.notFound().build();
        }

        teamService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    // Anidamos recursos

    @GetMapping("/{id}/players/{idPlayer}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Integer id, @PathVariable Integer idPlayer){

        PlayerDTO playerDTO =  new PlayerDTO(idPlayer,"Batistuta");

        if(playerDTO == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(playerDTO);
    }

    @GetMapping("/{id}/players")
    public ResponseEntity<List<PlayerDTO>> getTeamPlayers(@PathVariable Integer id){

        List<PlayerDTO> players = new ArrayList<>();
        players.add(new PlayerDTO(1, "Batistuta"));
        players.add(new PlayerDTO(2, "Dieguitoo"));

        return ResponseEntity.ok(players);
    }

}
