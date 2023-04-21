package myproject.firstproject.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import myproject.firstproject.domain.Board;
import myproject.firstproject.repository.BoardRepository;
import myproject.firstproject.service.BoardService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    //게시판 등록
    @PostMapping("/api/boards")
    public CreateBoardResponse createUser(@RequestBody @Valid CreateBoardRequest request) {
        Board board = Board.createBoard(request.getName());

        Long boardId = boardService.saveBoard(board);

        return new CreateBoardResponse(boardId);
    }

    //게시판 이름 수정
    @PostMapping("/api/boards/{id}")
    public UpdateBoardResponse updateBoard(@PathVariable("id") Long id,
                                           @RequestBody @Valid UpdateBoardRequest request) {
        boardService.updateName(id, request.getName());
        Board findBoard = boardService.findOneBoard(id);

        return new UpdateBoardResponse(findBoard.getId(), findBoard.getName());
    }

    //게시판 삭제
    @DeleteMapping("/api/boards/{id}")
    public DeleteBoardResponse deleteBoard(@PathVariable("id") Long id) {
        Board findBoard = boardService.findOneBoard(id);
        boardService.deleteBoard(findBoard);

        return new DeleteBoardResponse(id, findBoard.getName());
    }

    //게시판 조회
    @GetMapping("/api/boards")
    public List<BoardDto> boards() {
        List<Board> findBoards = boardService.findAllBoards();
        List<BoardDto> result = findBoards.stream()
                .map(b -> new BoardDto(b))
                .collect(Collectors.toList());

        return result;
    }

    @Getter
    @AllArgsConstructor
    static class BoardDto {
        private Long id;
        private String name;

        public BoardDto(Board board) {
            id = board.getId();
            name = board.getName();
        }
    }

    @Getter
    static class CreateBoardRequest {
        private String name;
    }

    @Getter
    @AllArgsConstructor
    static class CreateBoardResponse {
        private Long id;
    }

    @Getter
    static class UpdateBoardRequest {
        private String name;
    }

    @Getter
    @AllArgsConstructor
    static class UpdateBoardResponse {
        private Long id;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    static class DeleteBoardResponse {
        private Long id;
        private String name;
    }

}
