package project.tennisscoreboard.models.enums;

import lombok.Getter;

public enum GameScore {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);

    @Getter
    private int points;

    GameScore(int points) {
        this.points = points;
    }

    public GameScore next() {
        return GameScore.values()[this.ordinal() + 1];
    }
}
