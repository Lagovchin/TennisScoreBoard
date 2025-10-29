package project.tennisscoreboard.models.enums;

import lombok.Getter;

public enum SetScore {
    ZERO(0),
    ONE(1),
    TWO(2);

    SetScore(int points) {
        this.points = points;
    }

    @Getter
    private int points;

    public SetScore next() {
        return SetScore.values()[this.ordinal() + 1];
    }
}
