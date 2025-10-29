package project.tennisscoreboard.services.calculation;

import lombok.Setter;
import project.tennisscoreboard.models.enums.GameScore;
import project.tennisscoreboard.models.MatchScore;

@Setter
public class SuperGameService {

    private static final int SCORE_TO_WIN = 7;
    private static final int SCORE_DIFFERENCE_TO_WIN = 2;

    private SetScoreService setScoreService;

    public void plusPoint(MatchScore matchScore, String playerName) {

        String opponentName = getOpponentName(matchScore, playerName);

        int playerScore = matchScore.getSuperGameScore().get(playerName);
        int opponentScore = matchScore.getSuperGameScore().get(opponentName);

        matchScore.getSuperGameScore().put(playerName, playerScore + 1);

        int newPlayerScore = playerScore + 1;

        if (newPlayerScore >= SCORE_TO_WIN && (newPlayerScore - opponentScore) >= SCORE_DIFFERENCE_TO_WIN) {

            setScoreService.plusPoint(matchScore, playerName);
            resetScores(matchScore);
        }
    }

    private String getOpponentName(MatchScore matchScore, String playerName) {
        return matchScore.getPlayers().get(0).equals(playerName)
                ? matchScore.getPlayers().get(1)
                : matchScore.getPlayers().get(0);
    }

    private void resetScores(MatchScore matchScore) {

        matchScore.getPlayers().forEach(p -> {
            matchScore.getSuperGameScore().put(p, 0);
            matchScore.getScoreGames().put(p, GameScore.ZERO);
        });
        matchScore.setSuperGame(false);
    }
}