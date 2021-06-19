package hongcheol.socialMatchingWeb.controller;

import hongcheol.socialMatchingWeb.entity.Member;
import hongcheol.socialMatchingWeb.entity.Team;
import hongcheol.socialMatchingWeb.service.MatchingRequestService;
import hongcheol.socialMatchingWeb.service.MemberService;
import hongcheol.socialMatchingWeb.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MatchingRequestController {
    private final MatchingRequestService matchingRequestService;
    private final MemberService memberService;
    private final TeamService teamService;

    @GetMapping(value = "/matchingRequest")
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Team> teams = teamService.findTeams();

        model.addAttribute("members",members);
        model.addAttribute("teams",teams);

        return "matchingRequest/matchingRequestForm";
    }

    @PostMapping(value = "/matchingRequest")
    public String matchingRequest(@RequestParam("memberId") Long memberId, @RequestParam("teamId") Long teamId){
        matchingRequestService.matchingRequest(memberId,teamId);
        return "redirect:/matchingRequests";
    }

}
