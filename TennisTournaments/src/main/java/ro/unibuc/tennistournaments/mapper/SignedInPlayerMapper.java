package ro.unibuc.tennistournaments.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.tennistournaments.domain.SignedInPlayer;
import ro.unibuc.tennistournaments.dto.SignedInPlayerDto;

@Mapper(componentModel = "spring")
public interface SignedInPlayerMapper{
    SignedInPlayerDto mapToDto(SignedInPlayer signedInPlayer);
    SignedInPlayer mapToEntity(SignedInPlayerDto signedInPlayerDto);
}
