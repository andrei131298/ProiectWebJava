package ro.unibuc.tennistournaments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.tennistournaments.domain.SignedInList;
import ro.unibuc.tennistournaments.dto.SignedInListDto;
import ro.unibuc.tennistournaments.mapper.SignedInListMapper;
import ro.unibuc.tennistournaments.repository.SignedInListRepository;

@Service
public class SignedInListService {

    @Autowired
    private SignedInListRepository signedInListRepository;

    @Autowired
    private SignedInListMapper signedInListMapper;

    public SignedInListDto create(SignedInListDto signedInListDto) {
        SignedInList signedInList = signedInListMapper.mapToEntity(signedInListDto);
        SignedInList savedSignedInList = signedInListRepository.save(signedInList);

        return signedInListMapper.mapToDto(savedSignedInList);
    }

    public SignedInListDto getOne(Long id) {
        SignedInList signedInList = signedInListRepository.findById(id).get();
        return signedInListMapper.mapToDto(signedInList);

    }
    public SignedInListDto getByTournament(Long tournamentId) {
        SignedInList signedInList = signedInListRepository.find(tournamentId);
        return signedInListMapper.mapToDto(signedInList);
    }
}
