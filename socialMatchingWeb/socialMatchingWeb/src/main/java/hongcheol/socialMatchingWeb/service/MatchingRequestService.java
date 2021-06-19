package hongcheol.socialMatchingWeb.service;

import hongcheol.socialMatchingWeb.entity.*;
import hongcheol.socialMatchingWeb.repository.MatchingRequestRepository;
import hongcheol.socialMatchingWeb.repository.MemberRepository;
import hongcheol.socialMatchingWeb.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchingRequestService {
    private final MemberRepository memberRepository;
    private final MatchingRequestRepository matchingRequestRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public Long matchingRequest(Long memberId,Long teamId){
        Member member = memberRepository.findOne(memberId);
        Team team = teamRepository.findOne(teamId);

        TeamMatching teamMatching = new TeamMatching();
        teamMatching.setStatus(TeamMatchingStatus.READY);

        MatchTeam matchTeam = MatchTeam.createMatchTeam(team);
        MatchingRequest matchingRequest = MatchingRequest.createMatchingRequest(member,teamMatching,matchTeam);

        matchingRequestRepository.save(matchingRequest);
        return matchingRequest.getId();
    }

    @Transactional
    public void cancelMatchingRequest(Long matchingRequestId){
        MatchingRequest matchingRequest = matchingRequestRepository.findOne(matchingRequestId);
        matchingRequest.cancel();
    }
}
