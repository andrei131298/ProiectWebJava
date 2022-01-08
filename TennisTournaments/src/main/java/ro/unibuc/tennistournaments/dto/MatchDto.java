package ro.unibuc.tennistournaments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {
    private Long id;
    private Long tournamentId;
    private Integer playerPosition1;
    private Integer playerPosition2;
    private String result;
}

