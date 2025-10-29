package project.tennisscoreboard.services;

import lombok.Getter;
import lombok.Setter;
import project.tennisscoreboard.dao.PlayerDao;
import project.tennisscoreboard.models.MatchScore;
import project.tennisscoreboard.models.MatchScoreDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OnGoingMatchService {

    @Getter
    private static final OnGoingMatchService INSTANCE = new OnGoingMatchService();
    private MatchScoreDto matchScoreDto = new MatchScoreDto();

    private PlayerDao playerDao = new PlayerDao();

    @Getter
    @Setter
    private Map<UUID, MatchScore> matches = new ConcurrentHashMap<>();

    public UUID startMatch(List<String> players) {
        UUID uuid = UUID.randomUUID();
        players.forEach(s -> {
            playerDao.savePlayer(s);
        });
        matches.put(uuid, new MatchScore(uuid, players));
        return uuid;
    }

    public MatchScoreDto getMatchScoreDto(UUID uuid) {
        if (!matches.get(uuid).isSuperGame()) {
            return matchScoreDto.toMatchScoreDto(matches.get(uuid));
        } else {
            return matchScoreDto.toMatchSuperGameScoreDto(matches.get(uuid));
        }
    }

    public MatchScore getMatch(UUID uuid) {
        return matches.get(uuid);
    }

    public void deleteMatch(MatchScore matchScore) {
        matches.remove(matchScore.getMatchID());
    }
}