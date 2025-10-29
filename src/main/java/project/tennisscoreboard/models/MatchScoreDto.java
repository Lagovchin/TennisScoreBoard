package project.tennisscoreboard.models;

import lombok.Data;
import project.tennisscoreboard.models.enums.GameScore;
import project.tennisscoreboard.models.enums.SetScore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class MatchScoreDto {

    private UUID matchID;

    private List<String> players;

    private Map<String, String> scorePoints = new HashMap<>();
    private Map<String, GameScore> scoreGames = new HashMap<>();
    private Map<String, SetScore> scoreSets = new HashMap<>();

    public MatchScoreDto toMatchScoreDto(MatchScore matchScore) {
        MatchScoreDto matchScoreDto = new MatchScoreDto();
        matchScoreDto.setMatchID(matchScore.getMatchID());
        matchScoreDto.setPlayers(matchScore.getPlayers());
        matchScoreDto.setScoreGames(matchScore.getScoreGames());
        matchScoreDto.setScoreSets(matchScore.getScoreSets());
        matchScoreDto.setScorePoints(matchScore.getScorePoints().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getPoints())));
        return matchScoreDto;
    }

    public MatchScoreDto toMatchSuperGameScoreDto(MatchScore matchScore) {
        MatchScoreDto matchScoreDto = new MatchScoreDto();
        matchScoreDto.setMatchID(matchScore.getMatchID());
        matchScoreDto.setPlayers(matchScore.getPlayers());
        matchScoreDto.setScoreGames(matchScore.getScoreGames());
        matchScoreDto.setScoreSets(matchScore.getScoreSets());
        matchScoreDto.setScorePoints(matchScore.getSuperGameScore().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> String.valueOf(entry.getValue()))));
        return matchScoreDto;
    }
}
