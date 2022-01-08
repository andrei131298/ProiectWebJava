package ro.unibuc.tennistournaments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import ro.unibuc.tennistournaments.domain.Category;
import ro.unibuc.tennistournaments.domain.SignedInPlayer;
import ro.unibuc.tennistournaments.dto.*;
import ro.unibuc.tennistournaments.exception.ProjectException;
import ro.unibuc.tennistournaments.mapper.SignedInPlayerMapper;
import ro.unibuc.tennistournaments.repository.SignedInPlayerRepository;

import java.util.Date;
import java.util.List;

@Service
public class SignedInPlayerService {

    @Autowired private TournamentService tournamentService;
    @Autowired private SignedInListService signedInListService;
    @Autowired private PlayerService playerService;
    @Autowired private EmailSenderService emailService;
    @Autowired private CategoryService categoryService;

    private final SignedInPlayerRepository repository;
    private final SignedInPlayerMapper mapper;

    public SignedInPlayerService(SignedInPlayerRepository signedInPlayerRepository, SignedInPlayerMapper signedInPlayerMapper) {
        this.repository = signedInPlayerRepository;
        this.mapper = signedInPlayerMapper;
    }

    public SignedInPlayerDto create(SignedInPlayerDto signedInPlayerDto) {
        SignedInPlayer signedInPlayer = mapper.mapToEntity(signedInPlayerDto);

        SignedInListDto signedInListDto = signedInListService.getOne(signedInPlayer.getSignedInListId());
        PlayerDto playerDto = playerService.getOne(signedInPlayer.getPlayerId());
        Integer numberOfPlayersSignedIn = getNumberOfSignedInPlayers(signedInListDto.getId());
        TournamentDto tournamentDto = tournamentService.getOne(signedInListDto.getTournamentId());
        Category category = categoryService.getOne(tournamentDto.getCategoryId());
        if(category.getTax() > playerDto.getPocketBudget()){
            throw new ProjectException("This player doesn't have enough money to sign-in");
        }
        if(signedInListDto != null){
            if (tournamentDto != null) {

                if(playerDto.getLevel() != tournamentDto.getLevelAccepted()){
                    throw new ProjectException("This player doesn't have the accepted level for this tournament");
                }

                if(numberOfPlayersSignedIn >= tournamentDto.getNumberOfPlayers()){
                    throw new ProjectException("The sign-in list is full currently");
                }

                Date currentDate = new Date();
                if(currentDate.before(tournamentDto.getSignInStartDate()) || currentDate.after(tournamentDto.getSignInEndDate())) {
                    throw new ProjectException("We're currently not in the sign-in period");
                }
            }
        }

        SignedInPlayer duplicateSignIn = repository.find(signedInPlayer.getSignedInListId(), signedInPlayer.getPlayerId());
        if(duplicateSignIn != null){
            throw new ProjectException("This player already signed in");

        }

        SignedInPlayer savedSignedInPlayer = repository.save(signedInPlayer);
        try {
            emailService.sendSimpleEmail(playerDto.getEmail(), "Your sign-in was successfully registered, see you soon on the court :)", tournamentDto.getName() + " sign-in" );
        }
        catch (MailException e){
            System.out.println("Mail wasn't sent");
        }
        playerService.updatePocketBudgetReduce(signedInPlayer.getPlayerId(), category.getTax());
        return mapper.mapToDto(savedSignedInPlayer);
    }

    public Integer getNumberOfSignedInPlayers(Long signedInListId) {
        List<SignedInPlayer> signedInPlayers = repository.findBySignedInListId(signedInListId);
        return signedInPlayers.size();
    }

    public List<SignedInPlayer> getAllSignedInPlayersByList(Long signedInListId) {
        List<SignedInPlayer> signedInPlayers = repository.findBySignedInListId(signedInListId);
        return signedInPlayers;
    }
}
