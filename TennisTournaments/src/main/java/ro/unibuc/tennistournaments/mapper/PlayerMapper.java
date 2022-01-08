package ro.unibuc.tennistournaments.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.tennistournaments.domain.Player;
import ro.unibuc.tennistournaments.dto.PlayerDto;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerDto mapToDto(Player player);
    Player mapToEntity(PlayerDto playerDto);

}