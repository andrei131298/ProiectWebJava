package ro.unibuc.tennistournaments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.unibuc.tennistournaments.domain.PlayerPosition;
import ro.unibuc.tennistournaments.enums.TournamentPhase;

import java.util.List;

@Repository
public interface PlayerPositionRepository extends JpaRepository<PlayerPosition, Long> {

    @Transactional
    @Modifying
    @Query("update PlayerPosition p set p.hasLost = true where p.tournamentId = :tournamentId and p.position = :position")
    void updateHasLost(Long tournamentId, Integer position);

    @Transactional
    @Modifying
    @Query("update PlayerPosition p set p.currentPhase = :currentPhase where p.tournamentId = :tournamentId and p.position = :position")
    void updateCurrentPhase(Long tournamentId, Integer position, TournamentPhase currentPhase);

    @Query("SELECT p FROM PlayerPosition p WHERE p.tournamentId = :tournamentId AND p.position = :position")
    PlayerPosition findByPositionAndTournament(Long tournamentId, Integer position);

    @Query("SELECT p FROM PlayerPosition p WHERE p.tournamentId = :tournamentId ORDER BY p.position")
    List<PlayerPosition> findByTournament(Long tournamentId);

    @Query("SELECT p FROM PlayerPosition p WHERE p.tournamentId = :tournamentId AND p.position > :position AND p.currentPhase = :currentPhase order by p.position ASC")
    List<PlayerPosition> findOpponentPosition(Long tournamentId, Integer position, TournamentPhase currentPhase);
}
