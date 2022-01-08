package ro.unibuc.tennistournaments.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.tennistournaments.domain.PlayerPosition;
import ro.unibuc.tennistournaments.dto.PlayerPositionDto;

@Mapper(componentModel = "spring")
public interface PlayerPositionMapper {
    PlayerPositionDto mapToDto(PlayerPosition playerPosition);
    PlayerPosition mapToEntity(PlayerPositionDto playerPositionDto);
}
