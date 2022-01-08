package ro.unibuc.tennistournaments.util;

import ro.unibuc.tennistournaments.dto.CategoryDto;
import ro.unibuc.tennistournaments.enums.TournamentCategory;

public class CategoryDtoUtil {
    public static CategoryDto aCategoryDto(TournamentCategory tournamentCategory) {
        return CategoryDto.builder()
                .tournamentCategory(tournamentCategory)
                .prizeMoney(100)
                .tax(100)
                .build();
    }
}
