package project.tennisscoreboard.models;

import lombok.Data;

@Data
public class EndMatchDto {
    private String player1;
    private String player2;
    private String winner;

    public EndMatchDto toEndMatchDto(MatchScore matchScore) {

        EndMatchDto endMatchDto = new EndMatchDto();

        endMatchDto.setPlayer1(matchScore.getPlayers().get(0));
        endMatchDto.setPlayer1(matchScore.getPlayers().get(1));
        endMatchDto.setWinner(matchScore.getWinner());

        return endMatchDto;
    }
}
