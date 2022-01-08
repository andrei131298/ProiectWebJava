package ro.unibuc.tennistournaments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.tennistournaments.domain.Category;
import ro.unibuc.tennistournaments.domain.Match;
import ro.unibuc.tennistournaments.domain.PlayerPosition;
import ro.unibuc.tennistournaments.dto.PlayerDto;
import ro.unibuc.tennistournaments.dto.TournamentDto;
import ro.unibuc.tennistournaments.enums.TournamentPhase;
import ro.unibuc.tennistournaments.exception.ProjectException;

import java.util.ArrayList;


@Service
public class MatchService {

    @Autowired PlayerPositionService playerPositionService;
    @Autowired PlayerService playerService;
    @Autowired TournamentService tournamentService;
    @Autowired CategoryService categoryService;

    public Match postResult(Match match){
        String[] setStrings = match.getResult().split(" ");
        Integer setsWonByPlayer1 = 0;
        Integer setsWonByPlayer2 = 0;
        PlayerPosition playerPosition1 = playerPositionService.getByPositionAndTournament(match.getTournamentId(), match.getPlayerPosition1());
        PlayerPosition playerPosition2 = playerPositionService.getByPositionAndTournament(match.getTournamentId(), match.getPlayerPosition2());
        if(playerPosition1.getCurrentPhase() != playerPosition2.getCurrentPhase() || playerPosition1.getHasLost() == true || playerPosition2.getHasLost() == true){
            throw new ProjectException("The selected positions are not supposed to play, the current phase is different");
        }
        else {
            PlayerPosition opponentPosition = playerPositionService.findOpponentPosition(match.getTournamentId(), match.getPlayerPosition1(), playerPosition1.getCurrentPhase()).get(0);
            if(opponentPosition.getPosition() != match.getPlayerPosition2()){
                throw new ProjectException("The selected positions are not supposed to play");
            }

        }
        for(String set : setStrings){
            String[] games = set.split("-");
            Integer player1Games = Integer.parseInt(games[0]);
            Integer player2Games = Integer.parseInt(games[1]);
            ArrayList<Integer> occurrencesOf6 = new ArrayList<Integer>();
            ArrayList<Integer> occurrencesOf7 = new ArrayList<Integer>();
            for(int i = 0; i < set.length(); i++){
                if(set.charAt(i) == '6'){
                    occurrencesOf6.add(i);
                }
                if(set.charAt(i) == '7'){
                    occurrencesOf7.add(i);
                }
            }
            if(occurrencesOf6.size() <= 1 && occurrencesOf7.size() == 0 && Math.abs(player1Games - player2Games) >= 2){
                if(player1Games > player2Games){
                    setsWonByPlayer1 += 1;
                }
                else {
                    setsWonByPlayer2 += 1;
                }
            }
            if(occurrencesOf6.size() == 0 && occurrencesOf7.size() <= 1  && Math.abs(player1Games - player2Games) == 2){
                if(player1Games > player2Games){
                    setsWonByPlayer1 += 1;
                }
                else {
                    setsWonByPlayer2 += 1;
                }
            }
            if(occurrencesOf6.size() <= 1 && occurrencesOf7.size() <= 1  && Math.abs(player1Games - player2Games) == 1){
                if(player1Games > player2Games){
                    setsWonByPlayer1 += 1;
                }
                else {
                    setsWonByPlayer2 += 1;
                }
            }
        }
        if(setsWonByPlayer1 < 2 && setsWonByPlayer2 < 2){
            throw new ProjectException("The score is incorrect");
        }
        else{
            if(setsWonByPlayer1 > setsWonByPlayer2){
                playerPositionService.updateHasLost(match.getTournamentId(), match.getPlayerPosition2());
                int index = TournamentPhase.valueOf(playerPosition1.getCurrentPhase().toString()).ordinal();
                playerPositionService.updateCurrentPhase(match.getTournamentId(), match.getPlayerPosition1(), TournamentPhase.values()[index+1]);
                playerService.updatePoints(playerPosition1.getPlayerId(), 50);
                if(TournamentPhase.values()[index+1] == TournamentPhase.WINNER){
                    updatePocketForWinner(playerPosition1);
                }
            }
            else {
                playerPositionService.updateHasLost(match.getTournamentId(), match.getPlayerPosition1());
                int index = TournamentPhase.valueOf(playerPosition2.getCurrentPhase().toString()).ordinal();
                playerPositionService.updateCurrentPhase(match.getTournamentId(), match.getPlayerPosition2(), TournamentPhase.values()[index+1]);
                playerService.updatePoints(playerPosition2.getPlayerId(), 50);
                if(TournamentPhase.values()[index+1] == TournamentPhase.WINNER){
                    updatePocketForWinner(playerPosition2);
                }
            }
        }

        return match;
    }

    private void updatePocketForWinner(PlayerPosition winnerPosition){
        PlayerDto tournamentWinner = playerService.getOne(winnerPosition.getPlayerId());
        TournamentDto tournamentPlayed = tournamentService.getOne(winnerPosition.getTournamentId());
        Category tournamentCategory = categoryService.getOne(tournamentPlayed.getCategoryId());
        playerService.updatePocketBudget(tournamentWinner.getId(),tournamentCategory.getPrizeMoney());
    }
}
