package ar.com.besy.microservices.futbolmanagercore.services;

import ar.com.besy.microservices.futbolmanagercore.entities.TeamEntity;
import ar.com.besy.microservices.futbolmanagercore.mappers.TeamMapper;
import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
import ar.com.besy.microservices.futbolmanagercore.repositories.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
class TeamArgServiceTest {

   // @InjectMocks
    private TeamArgService teamServiceImpl; //la clase a someter

    @Mock
    private TeamRepository teamRepository; //clases que quiero aislar

    @Mock
    private TeamMapper teamMapper; //clases que quierpo aislar o simular

    private static List<TeamEntity> teamEntities;

    private static List<TeamDTO> teamDtos;

    private static PodamFactory factory;


    @BeforeAll
    static void setup() {

        //factory = new PodamFactoryImpl();
        //factory.getStrategy().setMemoization(false);

        teamEntities = new ArrayList<>();
        teamEntities.add(new TeamEntity(1,"River"));
        teamEntities.add(new TeamEntity(2,"Boca"));
        teamEntities.add(new TeamEntity(3,"Huracan"));

        teamDtos = new ArrayList<>(); //y una lista de dtos
        teamDtos.add(new TeamDTO(1,"River"));
        teamDtos.add(new TeamDTO(2,"Boca"));
        teamDtos.add(new TeamDTO(3,"Huracan"));

        /*
        for (int i = 0; i < 100; i++) {
            TeamEntity teamEntity = factory.manufacturePojoWithFullData(TeamEntity.class); //esto no se que hace
            teamEntities.add(teamEntity);

            //log.debug("Team entity {}", teamEntity);
            log.info("Team entity {}",teamEntity);

        }

        for (int i = 0; i < 100; i++) {

            TeamDTO teamDTO = factory.manufacturePojoWithFullData(TeamDTO.class); //aca tampoco sé
            teamDtos.add(teamDTO);
        }

        teamDtos = List.of(new TeamDTO(1, "River"),new TeamDTO(2, "Boca"),new TeamDTO(3, "Huracan"));
         */

    }

    @BeforeEach
    public void setUpBefore() {

        log.info("@BeforeAll - executes once before all test methods in this class");

        //MockitoAnnotations.initMocks(this);

        //teamServiceImpl = new TeamArgService(teamRepository, teamMapper);

        log.info("Build Mock for teamRepository");
        Mockito.when(teamRepository.findAll()).thenReturn(teamEntities); //simulo el uso de estos metdodos con infor que yo quiero darle
        Mockito.when(teamMapper.getTeamsDtos(teamEntities)).thenReturn(teamDtos); //ideam al anterior, se  dice que esta mockeando el metodo

        log.info("@BeforeEach - executed before each test method");

    }

    @Test
    @DisplayName("Test get team list")
    @Order(1)
    //@Disabled
    @EnabledOnOs(OS.WINDOWS)
    public void test_when_listTeam_then_returnAllTeams() {

        log.info("Get all teams");

        //Efective Test method
        List<TeamDTO> listAllTeams = teamServiceImpl.findAllTeams();

        //Mockito verify
        verify(teamRepository,atLeast(1)).findAll(); //verifican que realmente se haya hecho uso de los metodos a testear //test fidedigno
        verify(teamMapper,atLeast(1)).getTeamsDtos(teamEntities); //idem, si no llama al repository o al mapper , el test no pasa.

        //Asserts
        assertTrue(listAllTeams.size()>2); //aca verificamos la logica del negocio
        Optional<TeamDTO> first = listAllTeams.stream().findFirst();
        assertEquals(first.get().getTitle(),"Equipo River"); //por ejemplo que el primero tenga de titulo "Equipo River"
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    public final void sum(int i) {
        log.info("valor i " + i);
        assertTrue(i > 0);
    }



    @AfterEach
    void tearDown() {
        log.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        log.info("@AfterAll - executed after all test methods.");

    }


}