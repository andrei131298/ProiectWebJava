package ro.unibuc.tennistournaments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.tennistournaments.dto.SignedInListDto;
import ro.unibuc.tennistournaments.service.SignedInListService;

@Controller
@RequestMapping("/signedInList")
public class SignedInListController {
    @Autowired
    private SignedInListService signedInListService;

    @PostMapping()
    public ResponseEntity<SignedInListDto> createPlayer(@RequestBody SignedInListDto signedInListDto) {
        return ResponseEntity
                .ok()
                .body(signedInListService.create(signedInListDto));
    }

    @GetMapping("/{listId}")
    public ResponseEntity<SignedInListDto> getList(@PathVariable Long listId) {
        return ResponseEntity
                .ok()
                .body(signedInListService.getOne(listId));
    }
}
