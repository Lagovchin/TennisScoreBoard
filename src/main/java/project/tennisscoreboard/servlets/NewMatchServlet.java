package project.tennisscoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.tennisscoreboard.services.OnGoingMatchService;
import project.tennisscoreboard.utils.PlayerNameValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private OnGoingMatchService onGoingMatchService = OnGoingMatchService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("new-match.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String playerOne = req.getParameter("playerOne");
        String playerTwo = req.getParameter("playerTwo");
        String errorMessage = PlayerNameValidator.validatePlayers(playerOne, playerTwo);

        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("new-match.jsp").forward(req, resp);
            return;
        }


        List<String> players = new ArrayList<>();
        players.add(req.getParameter("playerOne"));
        players.add(req.getParameter("playerTwo"));

        UUID matchId = onGoingMatchService.startMatch(players);

        resp.sendRedirect("match-score?uuid=" + matchId);
    }
}