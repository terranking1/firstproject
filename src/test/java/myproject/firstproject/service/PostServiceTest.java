package myproject.firstproject.service;

import myproject.firstproject.domain.Board;
import myproject.firstproject.domain.Post;
import myproject.firstproject.domain.User;
import myproject.firstproject.repository.PostRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired EntityManager em;
    @Autowired PostService postService;
    @Autowired PostRepository postRepository;
    
    @Test
    public void 게시글_등록() throws Exception {
        //given
        User user = User.createUser("박정균", "테란킹");
        Board board = Board.createBoard("스타크래프트");
        em.persist(user);
        em.persist(board);
        String title = "123";
        String contents = "56";
        Long postId = postService.savePost(user.getId(), board.getId(), title, contents);

        //when

        //then
        Post getPost = postRepository.findOne(postId);

    }

}