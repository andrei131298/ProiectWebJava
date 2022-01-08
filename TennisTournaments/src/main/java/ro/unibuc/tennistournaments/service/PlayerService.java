package ro.unibuc.tennistournaments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.tennistournaments.domain.Player;
import ro.unibuc.tennistournaments.dto.PlayerDto;
import ro.unibuc.tennistournaments.enums.LevelType;
import ro.unibuc.tennistournaments.exception.ProjectException;
import ro.unibuc.tennistournaments.mapper.PlayerMapper;
import ro.unibuc.tennistournaments.repository.PlayerRepository;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMapper playerMapper;

    public PlayerDto create(PlayerDto playerDto) {
        Player player = playerMapper.mapToEntity(playerDto);
        Player savedPlayer = playerRepository.save(player);

        return playerMapper.mapToDto(savedPlayer);
    }

    public PlayerDto getOne(Long id) {
        Player player = playerRepository.findById(id).get();
        return playerMapper.mapToDto(player);
    }

    public PlayerDto getOneByEmail(String username) {
        return playerMapper.mapToDto(playerRepository.findByEmail(username).orElseThrow(() -> new ProjectException("No User")));

    }

    public Integer updatePocketBudget(Long playerId, Integer pocketBudget) {
        return playerRepository.updatePocketBudget(pocketBudget, playerId);
    }

    public Integer updatePocketBudgetReduce(Long playerId, Integer moneyReduced) {
        return playerRepository.updatePocketBudgetReduce(moneyReduced, playerId);
    }

    public Integer updatePoints(Long playerId, Integer points) {
        return playerRepository.updatePoints(
                points, playerId);
    }

    public List<Player> findAllByLevel(LevelType level){
        return playerRepository.findPlayersByLevel(level);
    }
}
