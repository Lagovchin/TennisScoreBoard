package project.tennisscoreboard.services;

import project.tennisscoreboard.services.calculation.*;

public class ServiceFactory {

    public static MatchScoreCalculationService createMatchScoreCalculationService() {

        GameScoreService gameScoreService = new GameScoreService();
        PointScoreService pointScoreService = new PointScoreService();
        SetScoreService setScoreService = new SetScoreService();
        MatchScoreCalculationService matchScoreService = new MatchScoreCalculationService();
        SuperGameService superGameService = new SuperGameService();

        superGameService.setSetScoreService(setScoreService);
        gameScoreService.setSetScoreService(setScoreService);
        gameScoreService.setSuperGameService(superGameService);
        pointScoreService.setGameScoreService(gameScoreService);
        matchScoreService.setPointScoreService(pointScoreService);

        return matchScoreService;
    }
}