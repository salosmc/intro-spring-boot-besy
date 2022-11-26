package ar.com.besy.microservices.futbolmanagercore.repositories;

import ar.com.besy.microservices.futbolmanagercore.model.TeamDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamDTO, Integer> {

    /*
    public List<TeamEntity> findByYearLessThan(int year);

    public List<TeamEntity> findByYearGreaterThanEqual(int edad);

    public List<TeamEntity> findByNameLike(String name);

    public List<TeamEntity> findByNameContaining(String name);

    @Query(value="select * from equipos where name = ?1 and year >= ?2 and year <= ?3" ,nativeQuery = true)
    public List<TeamEntity> findAllTeamsBetweenYearAndName(String name,int yearBegin,int yearEnd);
    */

}
