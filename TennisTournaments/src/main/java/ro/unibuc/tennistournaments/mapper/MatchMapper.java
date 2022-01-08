package ro.unibuc.tennistournaments.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.tennistournaments.domain.Match;
import ro.unibuc.tennistournaments.dto.MatchDto;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    MatchDto mapToDto(Match match);
    Match mapToEntity(MatchDto matchDto);
}
