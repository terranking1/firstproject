package myproject.firstproject.repository;

import lombok.RequiredArgsConstructor;
import myproject.firstproject.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public void delete(User user) {
        em.remove(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    public List<User> findByName(String name) {
        return em.createQuery("select u from User u where u.name = :name")
                .setParameter("name", name)
                .getResultList();
    }
}
