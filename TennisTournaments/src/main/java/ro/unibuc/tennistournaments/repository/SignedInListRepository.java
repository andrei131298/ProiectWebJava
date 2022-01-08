package ro.unibuc.tennistournaments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.unibuc.tennistournaments.domain.SignedInList;

@Repository
public interface SignedInListRepository extends JpaRepository<SignedInList, Long> {
    @Query(value = "SELECT s FROM SignedInList s WHERE s.tournamentId = :tournamentId")
    SignedInList find(Long tournamentId);
}
