package app.repository;

import app.models.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository("USER.JPA")
@Transactional
@Primary
public class UserRepositoryJpa extends AbstractEntityRepositoryJpa<User> {

    @PersistenceContext
    private EntityManager em;

    public UserRepositoryJpa(){super(User.class);}

    @Override
    public List<User> findAll() {
        TypedQuery<User> query =
                this.em.createQuery(
                        "select o from User o", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(long id) {
        return this.em.find(User.class, id);
    }

    @Override
    public User save(User user) {
        user = this.em.merge(user);
        return user;
    }

    @Override
    public User delete(long id) {
        User user = findById(id);
        if (user != null){
            this.em.remove(user);
        }
        return null;
    }

    @Override
    public List<User> findByQuery(String jpqlName, Object... params) {
        return null;
    }

}
