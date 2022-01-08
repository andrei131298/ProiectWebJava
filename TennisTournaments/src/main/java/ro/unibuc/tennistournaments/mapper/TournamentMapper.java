package ro.unibuc.tennistournaments.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.tennistournaments.domain.Tournament;
import ro.unibuc.tennistournaments.dto.TournamentDto;

@Mapper(componentModel = "spring")
public interface TournamentMapper {
    TournamentDto mapToDto(Tournament tournament);
    Tournament mapToEntity(TournamentDto tournamentDto);
}
