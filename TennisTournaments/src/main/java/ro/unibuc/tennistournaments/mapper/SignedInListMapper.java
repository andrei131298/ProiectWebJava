package ro.unibuc.tennistournaments.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.tennistournaments.domain.SignedInList;
import ro.unibuc.tennistournaments.dto.SignedInListDto;

@Mapper(componentModel = "spring")
public interface SignedInListMapper {

    SignedInListDto mapToDto(SignedInList signedInList);
    SignedInList mapToEntity(SignedInListDto signedInListDto);
}
