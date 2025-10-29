package project.tennisscoreboard.models.enums;

import lombok.Getter;

public enum PointScore {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    AD("AD");

    PointScore(String points) {
        this.points = points;
    }

    @Getter
    private String points;

    public PointScore next() {
        return PointScore.values()[this.ordinal() + 1];
    }


}
