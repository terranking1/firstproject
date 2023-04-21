package myproject.firstproject.repository;

import lombok.RequiredArgsConstructor;
import myproject.firstproject.domain.Board;
import myproject.firstproject.domain.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public void delete(Comment comment) {
        em.remove(comment);
    }

    public Comment findOne(Long id) {
        return em.find(Comment.class, id);
    }

    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c")
                .getResultList();
    }

    public List<Comment> findAllWithUserPost(int offset, int limit) {
        return em.createQuery(
                        "select c from Comment c" +
                                " join fetch c.user u" +
                                " join fetch c.post p", Comment.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
