package ro.unibuc.tennistournaments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.unibuc.tennistournaments.enums.TournamentCategory;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private TournamentCategory tournamentCategory;
    private Integer prizeMoney;
    private Integer tax;
}
