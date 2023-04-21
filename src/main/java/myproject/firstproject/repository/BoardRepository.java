package myproject.firstproject.repository;

import lombok.RequiredArgsConstructor;
import myproject.firstproject.domain.Board;
import myproject.firstproject.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void save(Board board) {
        em.persist(board);
    }

    public void delete(Board board) {
        em.remove(board);
    }

    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b")
                .getResultList();
    }

    public List<Board> findByName(String name) {
        return em.createQuery("select b from Board b where b.name= :name")
                .setParameter("name", name)
                .getResultList();
    }
}
