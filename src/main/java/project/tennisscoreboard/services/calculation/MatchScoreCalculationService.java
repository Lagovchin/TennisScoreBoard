package project.tennisscoreboard.services.calculation;

import lombok.Setter;
import project.tennisscoreboard.models.MatchScore;

@Setter
public class MatchScoreCalculationService {

    private PointScoreService pointScoreService;

    public void plusPoint(MatchScore matchScore, String playerName) {
        pointScoreService.plusPoint(matchScore, playerName);
    }
}