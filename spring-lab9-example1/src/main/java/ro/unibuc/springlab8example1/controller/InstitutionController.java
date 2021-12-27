package ro.unibuc.springlab8example1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.springlab8example1.dto.InstitutionDto;
import ro.unibuc.springlab8example1.service.InstitutionService;


@RestController
@RequestMapping("/institution")
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    @PostMapping()
    public ResponseEntity<InstitutionDto> createInstitution(@RequestBody InstitutionDto institutionDto) {
        return ResponseEntity
                .ok()
                .body(institutionService.create(institutionDto));
    }
}
