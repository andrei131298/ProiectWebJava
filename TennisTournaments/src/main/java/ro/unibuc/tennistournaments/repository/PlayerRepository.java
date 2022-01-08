package ro.unibuc.tennistournaments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.unibuc.tennistournaments.domain.Player;

import org.springframework.transaction.annotation.Transactional;
import ro.unibuc.tennistournaments.domain.SignedInList;
import ro.unibuc.tennistournaments.enums.LevelType;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Player p set p.pocketBudget = p.pocketBudget + :moneyAdded where p.id = :playerId")
    Integer updatePocketBudget(Integer moneyAdded, Long playerId);

    @Transactional
    @Modifying
    @Query("update Player p set p.pocketBudget = p.pocketBudget - :moneyReduced where p.id = :playerId")
    Integer updatePocketBudgetReduce(Integer moneyReduced, Long playerId);

    @Transactional
    @Modifying
    @Query("update Player p set p.points = p.points + :points where p.id = :playerId")
    Integer updatePoints(Integer points, Long playerId);

    @Query(value = "SELECT p FROM Player p WHERE p.level = :level ORDER BY p.points DESC")
    List<Player> findPlayersByLevel(LevelType level);
}
