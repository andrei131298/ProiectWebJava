package ro.unibuc.tennistournaments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.unibuc.tennistournaments.domain.Match;
import ro.unibuc.tennistournaments.exception.ProjectException;
import ro.unibuc.tennistournaments.service.MatchService;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    MatchService matchService;

    @PostMapping()
    public ResponseEntity postResult(@RequestBody Match match) {
        try{
        return ResponseEntity
                .ok()
                .body(matchService.postResult(match));
        }
        catch (ProjectException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
