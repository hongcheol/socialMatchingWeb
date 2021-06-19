package hongcheol.socialMatchingWeb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matching_request")
@Getter @Setter
public class MatchingRequest {

    @Id @GeneratedValue
    @Column(name = "matching_request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;//팀 매칭 요청 회원

    @OneToMany(mappedBy = "matchingRequest", cascade = CascadeType.ALL)
    private List<MatchTeam> matchTeams = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "team_matching_id")
    private TeamMatching teamMatching;

    private LocalDateTime requestDate;

    @Enumerated(EnumType.STRING)
    private MatchingStatus status;

    public void setMember(Member member){
        this.member = member;
        member.getMatchingRequests().add(this);
    }

    public void addMatchingRequest(MatchTeam matchTeam){
        this.matchTeams.add(matchTeam);
        matchTeam.setMatchingRequest(this);
    }

    public static MatchingRequest createMatchingRequest(Member member,TeamMatching teamMatching, MatchTeam... matchTeams){
        MatchingRequest matchingRequest = new MatchingRequest();
        matchingRequest.setMember(member);
        matchingRequest.setTeamMatching(teamMatching);
        for(MatchTeam matchTeam : matchTeams){
            matchingRequest.addMatchingRequest(matchTeam);
        }
        matchingRequest.setStatus(MatchingStatus.ON_JUDGE);
        matchingRequest.setRequestDate(LocalDateTime.now());
        return matchingRequest;
    }

    public void cancel(){
        if(teamMatching.getStatus() == TeamMatchingStatus.PASS){
            throw new IllegalStateException("승인이 완료된 팀 배정은 취소가 불가능합니다.");
        }
        this.setStatus(MatchingStatus.CANCEL);
        for(MatchTeam matchTeam : matchTeams){
            matchTeam.cancel();
        }
    }

    public void reject(){
        if(teamMatching.getStatus() == TeamMatchingStatus.PASS){
            throw new IllegalStateException("승인이 완료된 팀 배정은 거절이 불가능합니다.");
        }
        this.setStatus(MatchingStatus.CANCEL);
        for(MatchTeam matchTeam : matchTeams){
            matchTeam.reject();
        }
    }

}
