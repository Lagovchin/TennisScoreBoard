package project.tennisscoreboard.services.calculation;

import lombok.Setter;
import project.tennisscoreboard.models.enums.GameScore;
import project.tennisscoreboard.models.MatchScore;

public class GameScoreService {

    @Setter
    private SetScoreService setScoreService;
    @Setter
    private SuperGameService superGameService;
    private MatchScore matchScore;
    private String opponentName;

    public void plusGamePoint(MatchScore matchScore, String playerName) {

        this.matchScore = matchScore;
        opponentName = getOpponentName(playerName);
        GameScore playerScore = matchScore.getScoreGames().get(playerName);
        GameScore opponentScore = matchScore.getScoreGames().get(opponentName);

        if (matchScore.isSuperGame()) {
            superGameService.plusPoint(matchScore, playerName);
            return;
        }

        if (playerScore.ordinal() < GameScore.FIVE.ordinal()) {
            matchScore.getScoreGames().put(playerName, playerScore.next());
        } else if (playerScore.ordinal() == GameScore.FIVE.ordinal()) {
            if (opponentScore.ordinal() < GameScore.FIVE.ordinal()) {
                setScoreService.plusPoint(matchScore, playerName);
                setZeroScore();
            } else if (opponentScore.ordinal() == GameScore.FIVE.ordinal()) {
                matchScore.getScoreGames().put(playerName, playerScore.next());
            } else if (opponentScore.ordinal() == GameScore.SIX.ordinal()) {
                matchScore.getScoreGames().put(playerName, playerScore.next());
                matchScore.setSuperGame(true);
            }
        }
        if (playerScore.ordinal() == GameScore.SIX.ordinal()) {
            if (opponentScore.ordinal() <= GameScore.FIVE.ordinal()) {
                setScoreService.plusPoint(matchScore, playerName);
                setZeroScore();
            }
        }
    }

    public void setZeroScore() {
        matchScore.getPlayers().forEach(p -> matchScore.getScoreGames().put(p, GameScore.ZERO));
    }

    private String getOpponentName(String playerName) {
        return matchScore.getPlayers().get(0).equals(playerName)
                ? matchScore.getPlayers().get(1)
                : matchScore.getPlayers().get(0);
    }
}
