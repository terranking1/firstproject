package myproject.firstproject.service;

import lombok.RequiredArgsConstructor;
import myproject.firstproject.domain.Board;
import myproject.firstproject.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시판 등록
     */
    @Transactional
    public Long saveBoard(Board board) {
        boardRepository.save(board);

        return board.getId();
    }

    /**
     * 게시판 이름 수정
     */
    @Transactional
    public void updateName(Long id, String name) {
        Board findBoard = boardRepository.findOne(id);
        findBoard.changeName(name);
    }

    /**
     * 게시판 삭제
     */
    @Transactional
    public void deleteBoard(Board board) {
        boardRepository.delete(board);
    }

    /**
     * 게시판 조회
     */
    //특정 게시판 조회
    public Board findOneBoard(Long id) {
        return boardRepository.findOne(id);
    }

    //전체 게시판 조회
    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }
}
