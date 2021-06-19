package hongcheol.socialMatchingWeb.repository;

import hongcheol.socialMatchingWeb.entity.MatchingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MatchingRequestRepository {
    private final EntityManager em;

    public void save(MatchingRequest matchingRequest){
        em.persist(matchingRequest);
    }

    public MatchingRequest findOne(Long id){
        return em.find(MatchingRequest.class,id);
    }
}
