package ro.unibuc.tennistournaments.util;

import ro.unibuc.tennistournaments.domain.Player;
import ro.unibuc.tennistournaments.enums.LevelType;

public class PlayerUtil {
    public static Player aPlayer(String firstName, String lastName) {
        return Player.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email("andrei131298@yahoo.com")
                .level(LevelType.ADVANCED)
                .pocketBudget(0)
                .points(0)
                .build();
    }

    public static Player aUserWithId(long id) {
        return Player.builder()
                .id(id)
                .firstName("Andrei")
                .lastName("Alexandrescu")
                .email("andrei131298@yahoo.com")
                .level(LevelType.ADVANCED)
                .pocketBudget(0)
                .points(0)
                .build();
    }
}
