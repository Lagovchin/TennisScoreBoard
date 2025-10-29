package project.tennisscoreboard.utils;

public class PlayerNameValidator {

    public static String validatePlayers(String playerOne, String playerTwo) {
        if (playerOne == null || playerOne.trim().isEmpty()) {
            return "Имя игрока не может быть пустым.";
        }

        if (playerTwo == null || playerTwo.trim().isEmpty()) {
            return "Имя игрока не может быть пустым.";
        }

        String playerOneTrimmed = playerOne.trim();
        String playerTwoTrimmed = playerTwo.trim();

        if (playerOneTrimmed.equalsIgnoreCase(playerTwoTrimmed)) {
            return "Игрок не может играть сам с собой.";
        }

        if (playerOne.startsWith(" ") || playerOne.startsWith("\t")) {
            return "Имя не может начинаться с пробела";
        }

        if (playerTwo.startsWith(" ") || playerTwo.startsWith("\t")) {
            return "Имя не может начинаться с пробела";
        }

        return null;
    }
}
