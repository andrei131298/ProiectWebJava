package ro.unibuc.tennistournaments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.tennistournaments.domain.Tournament;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
