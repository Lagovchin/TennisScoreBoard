package project.tennisscoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.tennisscoreboard.models.MatchScore;
import project.tennisscoreboard.models.MatchScoreDto;
import project.tennisscoreboard.services.FinishedMatchesPersistenceService;
import project.tennisscoreboard.services.calculation.MatchScoreCalculationService;
import project.tennisscoreboard.services.OnGoingMatchService;
import project.tennisscoreboard.services.ServiceFactory;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private OnGoingMatchService onGoingMatchService = OnGoingMatchService.getINSTANCE();
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    public void init() throws ServletException {
        this.matchScoreCalculationService = ServiceFactory.createMatchScoreCalculationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScoreDto matchScoreDto = onGoingMatchService.getMatchScoreDto(uuid);
        req.setAttribute("matchScoreDto", matchScoreDto);
        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        String winPlayerName = req.getParameter("winner");
        MatchScore matchScore = onGoingMatchService.getMatch(matchId);
        matchScoreCalculationService.plusPoint(matchScore, winPlayerName);
        if (matchScore.getWinner() == null) {
            resp.sendRedirect("match-score?uuid=" + matchId);
        } else {
            onGoingMatchService.deleteMatch(matchScore);
            finishedMatchesPersistenceService.finishedMatch(matchScore);
            req.setAttribute("matchScore", matchScore);
            req.getRequestDispatcher("/end-match.jsp").forward(req, resp);
        }
    }
}