package ro.unibuc.springlab8example1.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.springlab8example1.domain.Institution;
import ro.unibuc.springlab8example1.dto.InstitutionDto;

@Mapper(componentModel = "spring")
public interface InstitutionMapper {

    InstitutionDto mapToDto(Institution institution);

    Institution mapToEntity(InstitutionDto institutionDto);
}
