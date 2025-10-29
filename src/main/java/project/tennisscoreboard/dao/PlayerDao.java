package project.tennisscoreboard.dao;

import org.hibernate.Session;
import project.tennisscoreboard.entity.Player;
import project.tennisscoreboard.utils.HibernateUtil;

import java.util.List;

public class PlayerDao {

    public void savePlayer(String player) {
        String sql = "INSERT IGNORE INTO Players (name) VALUES (:name)";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery(sql)
                    .setParameter("name", player)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    public Player getPlayerByName(String name) {
        Player player;
        String hql = "FROM Player WHERE name = :name";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            player = session.createQuery(hql, Player.class)
                    .setParameter("name", name)
                    .uniqueResult();
            session.getTransaction().commit();
        }
        return player;
    }

    public List<Player> getPlayers() {
        List<Player> players;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            players = session.createQuery("FROM Player ").list();
            session.getTransaction().commit();
        }
        return players;
    }
}
