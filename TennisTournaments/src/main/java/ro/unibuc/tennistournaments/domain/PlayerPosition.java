package ro.unibuc.tennistournaments.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.unibuc.tennistournaments.enums.TournamentPhase;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player_positions")
public class PlayerPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tournament_id")
    private Long tournamentId;

    @ManyToOne()
    @JoinColumn(name="tournament_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Tournament tournament;

    @Column(name = "position")
    private Integer position;

    @Column(name = "player_id")
    private Long playerId;

    @ManyToOne()
    @JoinColumn(name="player_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Player player;

    @Column(name = "has_lost", columnDefinition = "BOOLEAN")
    private Boolean hasLost;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_phase")
    private TournamentPhase currentPhase;
}
