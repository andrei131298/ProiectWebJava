package ro.unibuc.tennistournaments.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.unibuc.tennistournaments.domain.Player;
import ro.unibuc.tennistournaments.dto.PlayerDto;
import ro.unibuc.tennistournaments.mapper.PlayerMapper;
import ro.unibuc.tennistournaments.repository.PlayerRepository;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static ro.unibuc.tennistournaments.util.PlayerDtoUtil.aPlayerDto;
import static ro.unibuc.tennistournaments.util.PlayerUtil.aPlayer;
import static ro.unibuc.tennistournaments.util.PlayerUtil.aUserWithId;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayerMapper playerMapper;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void test_create() {
        //Arrange
        PlayerDto playerDto = aPlayerDto("Andrei", "Alexandrescu");
        Player player = aPlayer("Andrei", "Alexandrescu");
        Player savedPlayer = aUserWithId(1L);

        when(playerMapper.mapToEntity(playerDto)).thenReturn(player);
        when(playerRepository.save(player)).thenReturn(savedPlayer);
        when(playerMapper.mapToDto(savedPlayer)).thenReturn(playerDto);

        //Act
        PlayerDto result = playerService.create(playerDto);

        //Assert
        assertThat(result).isNotNull();
        verify(playerMapper, times(1)).mapToEntity(playerDto);
        verify(playerMapper, times(1)).mapToDto(savedPlayer);
        verify(playerRepository, times(1)).save(player);

        verifyNoMoreInteractions(playerMapper, playerRepository);
    }

    @Test
    void test_getOne_whenIdExists() {
        //arrange
        String email = "andrei131298@yahoo.com";
        PlayerDto playerDto = aPlayerDto("Andrei", "Alexandrescu");
        Player player = aPlayer("Andrei", "Alexandrescu");

        when(playerRepository.findByEmail(email)).thenReturn(Optional.of(player));
        when(playerMapper.mapToDto(player)).thenReturn(playerDto);

        //Act
        PlayerDto result = playerService.getOneByEmail(email);

        //Assert
        assertEquals(playerDto, result);
    }
}
