package ro.unibuc.tennistournaments.util;

import ro.unibuc.tennistournaments.dto.PlayerDto;
import ro.unibuc.tennistournaments.enums.LevelType;

public class PlayerDtoUtil {
    public static PlayerDto aPlayerDto(String firstName, String lastName) {
        return PlayerDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email("andrei131298@yahoo.com")
                .level(LevelType.ADVANCED)
                .pocketBudget(0)
                .points(0)
                .build();
    }
}
