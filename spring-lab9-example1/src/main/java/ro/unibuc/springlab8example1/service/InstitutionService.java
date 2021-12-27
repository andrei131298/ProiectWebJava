package ro.unibuc.springlab8example1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.springlab8example1.domain.Institution;
import ro.unibuc.springlab8example1.dto.InstitutionDto;
import ro.unibuc.springlab8example1.mapper.InstitutionMapper;
import ro.unibuc.springlab8example1.repository.InstitutionRepository;


@Service
public class InstitutionService {

    @Autowired
    InstitutionMapper institutionMapper;

    @Autowired
    InstitutionRepository institutionRepository;

    public InstitutionDto create(InstitutionDto institutionDto) {
        Institution institution = institutionMapper.mapToEntity(institutionDto);
        Institution savedInstitution = institutionRepository.save(institution);
        return institutionMapper.mapToDto(savedInstitution);
    }
}
