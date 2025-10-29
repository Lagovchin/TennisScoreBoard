package project.tennisscoreboard.dao;

import org.hibernate.Session;
import project.tennisscoreboard.entity.Match;
import project.tennisscoreboard.utils.HibernateUtil;

import java.util.List;

public class MatchDao {

    public void saveMatch(Match match) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }

    public List<Match> getAll(int page, int size) {
        String hql = "SELECT m FROM Match m JOIN FETCH m.player1 JOIN FETCH m.player2 JOIN FETCH m.winner ORDER BY m.id DESC";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(hql, Match.class)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .list();
        }
    }

    public List<Match> getMatchesByPlayerName(String playerName, int page, int size) {
        String hql = "SELECT m FROM Match m JOIN FETCH m.player1 JOIN FETCH m.player2 JOIN FETCH m.winner WHERE " +
                "m.player1.name LIKE :name OR m.player2.name LIKE :name ORDER BY m.id DESC";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(hql, Match.class)
                    .setParameter("name", "%" + playerName + "%")
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .list();
        }
    }

    public Long getMatchesCount() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT COUNT (m) FROM Match m", Long.class)
                    .uniqueResult();
        }
    }

    public Long getMatchesByPlayerNameCount(String playerName) {
        String hql = "SELECT COUNT (m) FROM Match m WHERE m.player1.name LIKE :name OR m.player2.name LIKE :name";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(hql, Long.class)
                    .setParameter("name", "%" + playerName + "%")
                    .uniqueResult();
        }
    }
}
