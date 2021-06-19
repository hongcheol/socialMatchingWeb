package hongcheol.socialMatchingWeb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name="order_item")
@Getter @Setter
public class MatchTeam {
    @Id @GeneratedValue
    @Column(name = "match_team_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_request_id")
    private MatchingRequest matchingRequest;

    public static MatchTeam createMatchTeam(Team team){
        MatchTeam matchTeam = new MatchTeam();
        matchTeam.setTeam(team);

        return matchTeam;
    }
    public void cancel(){

    }
    public void reject(){

    }
}
