package ro.unibuc.tennistournaments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.unibuc.tennistournaments.enums.LevelType;
import ro.unibuc.tennistournaments.enums.TournamentType;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TournamentDto {

    private Long id;
    private String name;
    private LevelType levelAccepted;
    private Date startDate;
    private Date endDate;
    private Date signInStartDate;
    private Date signInEndDate;
    private Integer numberOfPlayers;
    private TournamentType tournamentType;
    private Long categoryId;

}
