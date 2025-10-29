package project.tennisscoreboard.models;


import lombok.Data;
import project.tennisscoreboard.models.enums.GameScore;
import project.tennisscoreboard.models.enums.PointScore;
import project.tennisscoreboard.models.enums.SetScore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class MatchScore {

    public MatchScore(UUID matchID, List<String> players) {
        this.players = players;
        this.matchID = matchID;

        setZeroScore(players);
    }

    private UUID matchID;
    private String winner;
    private boolean isSuperGame;

    private List<String> players;

    private Map<String, PointScore> scorePoints = new HashMap<>();
    private Map<String, GameScore> scoreGames = new HashMap<>();
    private Map<String, SetScore> scoreSets = new HashMap<>();
    private Map<String, Integer> superGameScore = new HashMap<>();

    private void setZeroScore(List<String> players) {

        players.forEach(s -> {
            scorePoints.put(s, PointScore.ZERO);
            scoreGames.put(s, GameScore.ZERO);
            scoreSets.put(s, SetScore.ZERO);
            superGameScore.put(s, 0);
        });
    }
}
