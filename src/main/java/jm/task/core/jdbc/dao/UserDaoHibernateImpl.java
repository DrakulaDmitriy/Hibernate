package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UserDaoHibernateImpl implements UserDao {

    private static final String CREATE_TABLE_USERS_SQL = "CREATE TABLE IF NOT EXISTS users" +
            "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(32) NOT NULL, " +
            "lastName VARCHAR(32) NOT NULL, " +
            "age TINYINT NOT NULL)";
    private static final String DROP_TABLE_USERS_SQL = "DROP TABLE IF EXISTS users";
    private static final String GET_ALL_USERS_HQL = "FROM User";
    private static final String CLEAN_TABLE_HQL = "DELETE FROM User";

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE_USERS_SQL).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(DROP_TABLE_USERS_SQL).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            userList = session.createQuery(GET_ALL_USERS_HQL).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery(CLEAN_TABLE_HQL).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
