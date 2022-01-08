package ro.unibuc.tennistournaments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.unibuc.tennistournaments.dto.TournamentDto;
import ro.unibuc.tennistournaments.service.TournamentService;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @PostMapping()
    public ResponseEntity<TournamentDto> createTournament(@RequestBody TournamentDto tournamentDto) {
        return ResponseEntity
                .ok()
                .body(tournamentService.create(tournamentDto));
    }

}
