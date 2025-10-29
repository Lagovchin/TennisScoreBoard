package project.tennisscoreboard.services.calculation;

import lombok.Setter;
import project.tennisscoreboard.models.MatchScore;
import project.tennisscoreboard.models.enums.SetScore;

@Setter
public class SetScoreService {

    public void plusPoint(MatchScore matchScore, String playerName) {

        SetScore playerSetScore = matchScore.getScoreSets().get(playerName);

        if (playerSetScore.ordinal() == SetScore.ONE.ordinal()) {
            matchScore.getScoreSets().put(playerName, playerSetScore.next());
            matchScore.setWinner(playerName);
        } else {
            matchScore.getScoreSets().put(playerName, playerSetScore.next());
        }
    }
}