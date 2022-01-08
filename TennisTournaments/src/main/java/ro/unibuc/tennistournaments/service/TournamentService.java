package ro.unibuc.tennistournaments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.tennistournaments.domain.Tournament;
import ro.unibuc.tennistournaments.dto.TournamentDto;
import ro.unibuc.tennistournaments.mapper.TournamentMapper;
import ro.unibuc.tennistournaments.repository.TournamentRepository;

@Service
public class TournamentService {

    @Autowired TournamentRepository tournamentRepository;

    @Autowired
    private TournamentMapper tournamentMapper;

    public TournamentDto create(TournamentDto tournamentDto) {
        Tournament tournament = tournamentMapper.mapToEntity(tournamentDto);
        Tournament savedTournament = tournamentRepository.save(tournament);

        return tournamentMapper.mapToDto(savedTournament);
    }

    public TournamentDto getOne(Long id) {
        Tournament tournament = tournamentRepository.findById(id).get();
        return tournamentMapper.mapToDto(tournament);
    }
}

