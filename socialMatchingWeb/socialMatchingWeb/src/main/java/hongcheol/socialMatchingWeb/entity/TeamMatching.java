package hongcheol.socialMatchingWeb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter @Setter
public class TeamMatching {
    @Id @GeneratedValue
    @Column(name = "team_matching_id")
    private Long id;

    @OneToOne(mappedBy = "teamMatching",fetch = FetchType.LAZY)
    private MatchingRequest matchingRequest;

    @Enumerated(EnumType.STRING)
    private TeamMatchingStatus status;
}
