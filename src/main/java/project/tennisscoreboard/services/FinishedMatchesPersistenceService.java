package project.tennisscoreboard.services;

import project.tennisscoreboard.dao.MatchDao;
import project.tennisscoreboard.dao.PlayerDao;
import project.tennisscoreboard.entity.Match;
import project.tennisscoreboard.entity.Player;
import project.tennisscoreboard.models.FinishedMatchDto;
import project.tennisscoreboard.models.MatchScore;

import java.util.List;

public class FinishedMatchesPersistenceService {

    private static final int MAX_PAGE_SIZE = 5;

    PlayerDao playerDao = new PlayerDao();
    MatchDao matchDao = new MatchDao();

    public void finishedMatch(MatchScore matchScore) {
        Player player1 = playerDao.getPlayerByName(matchScore.getPlayers().get(0));
        Player player2 = playerDao.getPlayerByName(matchScore.getPlayers().get(1));
        Player winner = playerDao.getPlayerByName(matchScore.getWinner());

        Match match = new Match(player1, player2, winner);
        matchDao.saveMatch(match);
    }

    public FinishedMatchDto getFinishedMatches(String playerName, String page) {
        List<Match> matches;
        Long matchesCount;
        int currentPage = parsePage(page);

        if (playerName == null || playerName.isBlank()) {
            matchesCount = getMatchesCount();
            matches = getAllFinishedMatches(currentPage, MAX_PAGE_SIZE);
        } else {
            matches = getFinishedMatchesByPlayerName(playerName, currentPage);
            matchesCount = getMatchesByPlayerNameCount(playerName);
        }

        int pagesCount = (int) Math.ceil((double) matchesCount / MAX_PAGE_SIZE);

        return new FinishedMatchDto(matches, currentPage, MAX_PAGE_SIZE, matchesCount, pagesCount);
    }

    private List<Match> getAllFinishedMatches(int page, int MAX_PAGE_SIZE) {
        return matchDao.getAll(page, MAX_PAGE_SIZE);
    }

    private List<Match> getFinishedMatchesByPlayerName(String playerName, int page) {
        return matchDao.getMatchesByPlayerName(playerName, page, MAX_PAGE_SIZE);
    }

    private Long getMatchesCount() {
        return matchDao.getMatchesCount();
    }

    private Long getMatchesByPlayerNameCount(String playerName) {
        return matchDao.getMatchesByPlayerNameCount(playerName);
    }

    private int parsePage(String page) {
        if (page == null || page.isBlank()) {
            return 1;
        }
        return Math.max(1, Integer.parseInt(page));
    }
}
