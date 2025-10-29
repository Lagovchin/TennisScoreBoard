package project.tennisscoreboard.services.calculation;

import lombok.Setter;
import project.tennisscoreboard.models.MatchScore;
import project.tennisscoreboard.models.enums.PointScore;

public class PointScoreService {

    @Setter
    private GameScoreService gameScoreService;
    private MatchScore matchScore;
    private String opponentName;

    public void plusPoint(MatchScore matchScore, String playerName) {

        if (matchScore.isSuperGame()) {
            gameScoreService.plusGamePoint(matchScore, playerName); // Это вызовет делегирование в SuperGameService
            return;
        }

        this.matchScore = matchScore;
        opponentName = getOpponentName(playerName);
        PointScore pointScore = matchScore.getScorePoints().get(playerName);
        PointScore opponentPointScore = matchScore.getScorePoints().get(opponentName);

        if (pointScore.ordinal() <= PointScore.THIRTY.ordinal()) {
            PointScore newScore = pointScore.next();
            matchScore.getScorePoints().put(playerName, newScore);
        } else if (pointScore == PointScore.FORTY) {
            if (opponentPointScore == PointScore.AD) {
                matchScore.getScorePoints().put(opponentName, PointScore.FORTY);
            } else if (opponentPointScore == PointScore.FORTY) {
                matchScore.getScorePoints().put(playerName, PointScore.AD);
            } else {
                gameScoreService.plusGamePoint(matchScore, playerName);
                setZeroScore();
            }
        } else if (pointScore == PointScore.AD) {
            gameScoreService.plusGamePoint(matchScore, playerName);
            setZeroScore();
        }
    }

    private String getOpponentName(String playerName) {
        return matchScore.getPlayers().get(0).equals(playerName)
                ? matchScore.getPlayers().get(1)
                : matchScore.getPlayers().get(0);
    }

    private void setZeroScore() {
        matchScore.getPlayers().forEach(p -> matchScore.getScorePoints().put(p, PointScore.ZERO));
    }
}