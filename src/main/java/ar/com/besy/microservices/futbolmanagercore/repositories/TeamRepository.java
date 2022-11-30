package ar.com.besy.microservices.futbolmanagercore.repositories;

import ar.com.besy.microservices.futbolmanagercore.entities.TeamEntity;
import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    /*
    public List<TeamDTO> findByYearLessThan(int year);

    public List<TeamDTO> findByYearGreaterThanEqual(int edad);

    public List<TeamDTO> findByNameLike(String name);

    public List<TeamDTO> findByNameContaining(String name);

    @Query(value="select * from equipos where name = ?1 and year >= ?2 and year <= ?3" ,nativeQuery = true)
    public List<TeamDTO> findAllTeamsBetweenYearAndName(String name,int yearBegin,int yearEnd);
    */

    public List<TeamEntity> findByYearLessThan(int year);

    public List<TeamEntity> findByYearGreaterThanEqual(int edad);

    public List<TeamEntity> findByNameLike(String name);

    public List<TeamEntity> findByNameContaining(String name);

    @Query(value="select * from equipos where name = ?1 and year >= ?2 and year <= ?3" ,nativeQuery = true)
    public List<TeamEntity> findAllTeamsBetweenYearAndName(String name,int yearBegin,int yearEnd);

}
