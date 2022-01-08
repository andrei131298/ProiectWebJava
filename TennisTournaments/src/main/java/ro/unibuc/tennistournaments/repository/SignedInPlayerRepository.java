package ro.unibuc.tennistournaments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.unibuc.tennistournaments.domain.SignedInList;
import ro.unibuc.tennistournaments.domain.SignedInPlayer;

import java.util.List;

@Repository
public interface SignedInPlayerRepository extends JpaRepository<SignedInPlayer, Long> {

    @Query(value = "SELECT s FROM SignedInPlayer s WHERE s.playerId = :playerId AND s.signedInListId = :signedInListId")
    SignedInPlayer find(Long signedInListId, Long playerId);

    List<SignedInPlayer> findBySignedInListId(Long signedInListId);

}
