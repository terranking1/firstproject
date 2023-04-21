package myproject.firstproject.service;

import myproject.firstproject.domain.Board;
import myproject.firstproject.repository.BoardRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired private BoardRepository boardRepository;
    @Autowired private BoardService boardService;

    @Test
    public void 게시판_등록() throws Exception {
        //given
        Board board = new Board();
        board.setName("잡담");
        //when
        Long saveId = boardService.saveBoard(board);
        //then
        Assertions.assertEquals(board, boardRepository.findOne(saveId));
     }

     @Test
     public void 게시판_삭제() throws Exception {
         //given
         Board board = new Board();
         board.setName("잡담");
         //when
         Long saveId = boardService.saveBoard(board);
         boardService.deleteBoard(boardService.findOneBoard(saveId));
         //then
         Assertions.assertEquals(0, boardRepository.findAll().size());
      }

}