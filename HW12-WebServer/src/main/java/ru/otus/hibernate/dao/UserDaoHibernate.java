package ru.otus.hibernate.dao;

import org.hibernate.SessionFactory;
import ru.otus.dao.UserDao;
import ru.otus.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class UserDaoHibernate implements UserDao{
    private static Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);

    private final SessionFactory factory;

    public UserDaoHibernate(SessionFactory factory) {
        this.factory =factory;
        init();
    }



    @Override
    public Optional<User> findById(long id) {
        try(var session = factory.openSession()) {
            return Optional.ofNullable(session.find(User.class, id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    public List<User> findAll() {

        try(var session = factory.openSession()) {
            return session.createQuery("select u from User u").list();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return List.of();
    }

    @Override
    public long saveUser(User user) {
        var session = factory.openSession();
        var transaction = session.beginTransaction();
        try {
            session.save(user);
            session.flush();
            transaction.commit();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            transaction.rollback();
        }finally {
            session.close();
        }
        return user.getId();
    }


    @Override
    public Optional<User> findRandomUser() {
        Random r = new Random();
        try( var session = factory.openSession()) {
            var size = session.createQuery("select count(id) from User").list().get(0);
            var id = r.nextInt((int) (long) size);
            return Optional.ofNullable(session.find(User.class, (long) id));
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try(var session =factory.openSession()) {
            var users = session.createQuery("select u from User u where u.login = ?").setParameter(0, login).list();
            if (!users.isEmpty()) {
                return Optional.ofNullable((User) users.get(0));
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    private void init(){
        var session =factory.openSession();
        var transaction= session.beginTransaction();
        try{
            List.of(new User(1L, "Крис Гир", "user1", "11111"),
                    new User(2L, "Ая Кэш", "user2", "11111"),
                    new User(3L, "Десмин Боргес", "user3", "11111"),
                    new User(4L, "Кетер Донохью", "user4", "11111"),
                    new User(5L, "Стивен Шнайдер", "user5", "11111"),
                    new User(6L, "Джанет Вэрни", "user6", "11111"),
                    new User(7L, "Брэндон Смит", "user7", "11111"))
                    .forEach(user->session.save(user));
            session.flush();
            transaction.commit();
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            transaction.rollback();
        }finally {
            session.close();
        }
    }
}
