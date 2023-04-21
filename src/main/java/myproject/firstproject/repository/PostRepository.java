package myproject.firstproject.repository;

import lombok.RequiredArgsConstructor;
import myproject.firstproject.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public void delete(Post post) {
        em.remove(post);
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p")
                .getResultList();
    }

    public List<Post> findByName(String name) {
        return em.createQuery("select p from Post p where p.name= :name")
                .setParameter("name", name)
                .getResultList();
    }

    public List<Post> findAllWithUserBoard(int offset, int limit) {
        return em.createQuery(
                        "select p from Post p" +
                                " join fetch p.user u" +
                                " join fetch p.board b", Post.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
