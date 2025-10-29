package project.tennisscoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.tennisscoreboard.models.FinishedMatchDto;
import project.tennisscoreboard.services.FinishedMatchesPersistenceService;

import java.io.IOException;

@WebServlet("/matches")
public class EndMatchServlet extends HttpServlet {

    private FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerName = req.getParameter("filter_by_player_name");
        String page = req.getParameter("page");

        FinishedMatchDto matchPage = finishedMatchesPersistenceService.getFinishedMatches(playerName, page);

        req.setAttribute("matchPage", matchPage);
        req.setAttribute("filterPlayerName", playerName != null ? playerName : "");
        req.getRequestDispatcher("/matches.jsp").forward(req, resp);
    }
}
