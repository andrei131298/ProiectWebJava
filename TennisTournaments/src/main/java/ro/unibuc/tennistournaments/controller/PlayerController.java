package ro.unibuc.tennistournaments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.tennistournaments.domain.Player;
import ro.unibuc.tennistournaments.dto.PlayerDto;
import ro.unibuc.tennistournaments.enums.LevelType;
import ro.unibuc.tennistournaments.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping()
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto playerDto) {
        return ResponseEntity
                .ok()
                .body(playerService.create(playerDto));
    }

    @PatchMapping("/{playerId}/{moneyAdded}")
    public ResponseEntity<Integer> update(@PathVariable Long playerId, @PathVariable Integer moneyAdded) {
        return ResponseEntity.ok(playerService.updatePocketBudget(playerId, moneyAdded));
    }

    @GetMapping("/{level}")
    public ResponseEntity<List<Player>> findAllByLevel(@PathVariable LevelType level){
        return ResponseEntity.ok(playerService.findAllByLevel(level));
    }

}
