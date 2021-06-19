package hongcheol.socialMatchingWeb.repository;

import hongcheol.socialMatchingWeb.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeamRepository {
    private final EntityManager em;

    public void save(Team team){
        if(team.getId() == null){
            em.persist(team);
        }
        else{
            em.merge(team);
        }
    }

    public Team findOne(Long id){
        return em.find(Team.class,id);
    }

    public List<Team> findAll(){
        return em.createQuery("select t from Team t",Team.class)
                .getResultList();
    }

}
