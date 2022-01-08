package ro.unibuc.tennistournaments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.tennistournaments.domain.PlayerPosition;
import ro.unibuc.tennistournaments.domain.SignedInPlayer;
import ro.unibuc.tennistournaments.dto.PlayerDto;
import ro.unibuc.tennistournaments.dto.SignedInListDto;
import ro.unibuc.tennistournaments.dto.TournamentDto;
import ro.unibuc.tennistournaments.enums.TournamentPhase;
import ro.unibuc.tennistournaments.repository.PlayerPositionRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PlayerPositionService {

    @Autowired private PlayerPositionRepository playerPositionRepository;
    @Autowired private TournamentService tournamentService;
    @Autowired private SignedInListService signedInListService;
    @Autowired private SignedInPlayerService signedInPlayerService;
    @Autowired private PlayerService playerService;
    @Autowired private EmailSenderService emailService;

    public List<PlayerDto> create(Long tournamentId) {
        TournamentDto tournamentDto = tournamentService.getOne(tournamentId);
        SignedInListDto signedInListDto = signedInListService.getByTournament(tournamentDto.getId());
        List<SignedInPlayer> signedInPlayers = signedInPlayerService.getAllSignedInPlayersByList(signedInListDto.getId());
        List<PlayerDto> players = new ArrayList<PlayerDto>();
        for(SignedInPlayer p : signedInPlayers){
            players.add(playerService.getOne(p.getPlayerId()));
         }
        List<Integer> randomPositions = new ArrayList<Integer>();
        for (int i = 1; i <= players.size(); i++)
            randomPositions.add(i);
        Collections.shuffle(randomPositions);
        for(int i = 0; i < players.size(); i++) {
            TournamentPhase firstPhase = findTournamentFirstPhase(tournamentDto);
            PlayerPosition playerPosition = PlayerPosition.builder().tournamentId(tournamentId).position(randomPositions.get(i)).playerId(players.get(i).getId()).hasLost(false).currentPhase(firstPhase).build();
            playerPositionRepository.save(playerPosition);
            emailService.sendSimpleEmail(players.get(i).getEmail(), "The draw for the ", tournamentDto.getName() + " tournament has been published" );
        }
        return players;
    }

    public void updateHasLost(Long tournamentId, Integer position){
        playerPositionRepository.updateHasLost(tournamentId, position);
    }

    public void updateCurrentPhase(Long tournamentId, Integer position, TournamentPhase phase){
        playerPositionRepository.updateCurrentPhase(tournamentId, position, phase);
    }

    public PlayerPosition getByPositionAndTournament(Long tournamentId, Integer position){
        return playerPositionRepository.findByPositionAndTournament(tournamentId, position);
    }

    public List<PlayerPosition> getByTournament(Long tournamentId){
        return playerPositionRepository.findByTournament(tournamentId);
    }

    public List<PlayerPosition> findOpponentPosition(Long tournamentId, Integer position, TournamentPhase phase){
        return playerPositionRepository.findOpponentPosition(tournamentId, position, phase);
    }

    public TournamentPhase findTournamentFirstPhase(TournamentDto tournamentDto){
        switch (tournamentDto.getNumberOfPlayers()){
            case 4:
                return TournamentPhase.SEMI_FINALS;
            case 8:
                return TournamentPhase.QUARTER_FINALS;
            case 16:
                return TournamentPhase.ROUND_OF_16;
        }
        return null;
    }
}
