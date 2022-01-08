package ro.unibuc.tennistournaments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignedInPlayerDto {
    private Long signedInListId;
    private Long playerId;

}
