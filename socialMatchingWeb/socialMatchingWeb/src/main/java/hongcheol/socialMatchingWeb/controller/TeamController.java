package hongcheol.socialMatchingWeb.controller;

import hongcheol.socialMatchingWeb.entity.Team;
import hongcheol.socialMatchingWeb.form.TeamForm;
import hongcheol.socialMatchingWeb.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping(value = "/teams/new")
    public String createForm(Model model){
        model.addAttribute("form",new TeamForm());
        return "teams/createTeamForm";
    }

    @PostMapping(value = "/teams/new")
    public String create(TeamForm form){
        Team team = new Team();
        team.setName(form.getName());
        teamService.saveTeam(team);
        return "redirect:/teams";
    }

    @GetMapping(value = "/teams")
    public String list(Model model){
        List<Team> teams = teamService.findTeams();
        model.addAttribute("teams",teams);
        return "teams/teamList";
    }
    @GetMapping(value ="/teams/{teamId}/edit")
    public String editTeamForm(@PathVariable("teamId") Long teamId, Model model){
        Team team = (Team) teamService.findOne(teamId);
        TeamForm form = new TeamForm();
        form.setId(team.getId());
        form.setName(team.getName());

        model.addAttribute("form",form);
        return "teams/updateTeamForm";
    }
    @PostMapping(value = "/teams/{teamId}/edit")
    public String updateTeam(@ModelAttribute("form") TeamForm form){

        teamService.updateTeam(form.getId(),form.getName());
        return "redirect:/teams";
    }
}
