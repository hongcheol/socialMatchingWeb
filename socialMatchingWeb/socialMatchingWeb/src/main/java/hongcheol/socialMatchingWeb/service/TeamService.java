package hongcheol.socialMatchingWeb.service;

import hongcheol.socialMatchingWeb.entity.Team;
import hongcheol.socialMatchingWeb.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    @Transactional
    public void saveTeam(Team team){
        teamRepository.save(team);
    }
    public List<Team> findTeams(){
        return teamRepository.findAll();
    }
    public Team findOne(Long teamId){
        return teamRepository.findOne(teamId);
    }
    @Transactional
    public void updateTeam(Long id,String name){
        Team team = teamRepository.findOne(id);
        team.setName(name);
    }
}
