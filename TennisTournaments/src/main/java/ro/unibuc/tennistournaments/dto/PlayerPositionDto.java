package ro.unibuc.tennistournaments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.unibuc.tennistournaments.enums.TournamentPhase;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPositionDto {
    private Long id;
    private Long tournamentId;
    private Integer position;
    private Long playerId;
    private Boolean hasLost;
    private TournamentPhase currentPhase;
}
