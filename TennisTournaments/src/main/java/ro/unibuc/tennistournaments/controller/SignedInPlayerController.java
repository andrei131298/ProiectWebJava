package ro.unibuc.tennistournaments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.unibuc.tennistournaments.dto.SignedInPlayerDto;
import ro.unibuc.tennistournaments.exception.ProjectException;
import ro.unibuc.tennistournaments.service.SignedInPlayerService;

@Controller
@RequestMapping("/signedInPlayer")
public class SignedInPlayerController {

    @Autowired
    private SignedInPlayerService signedInPlayerService;

    @PostMapping()
    public ResponseEntity createPlayer(@RequestBody SignedInPlayerDto signedInPlayerDto) {
        try {
            return ResponseEntity
                    .ok()
                    .body(signedInPlayerService.create(signedInPlayerDto));
        }
        catch (ProjectException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
