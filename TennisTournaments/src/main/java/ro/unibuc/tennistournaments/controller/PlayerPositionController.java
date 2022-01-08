package ro.unibuc.tennistournaments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.tennistournaments.domain.PlayerPosition;
import ro.unibuc.tennistournaments.dto.PlayerDto;
import ro.unibuc.tennistournaments.dto.SignedInListDto;
import ro.unibuc.tennistournaments.service.PlayerPositionService;

import java.util.List;

@RestController
@RequestMapping("/playerPosition")
public class PlayerPositionController {

    @Autowired
    private PlayerPositionService playerPositionService;

    @PostMapping("/{tournamentId}")
    public ResponseEntity<List<PlayerDto>> createPlayerPositions(@PathVariable Long tournamentId) {
        return ResponseEntity
                .ok()
                .body(playerPositionService.create(tournamentId));
    }

    @GetMapping("/{tournamentId}")
    public ResponseEntity<List<PlayerPosition>> getAllPlayerPositions(@PathVariable Long tournamentId) {
        return ResponseEntity
                .ok()
                .body(playerPositionService.getByTournament(tournamentId));
    }
}
