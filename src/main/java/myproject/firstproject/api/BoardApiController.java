package myproject.firstproject.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import myproject.firstproject.api.dto.board.*;
import myproject.firstproject.domain.Board;
import myproject.firstproject.service.BoardService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "board", description = "게시판 API")
@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    //게시판 조회
    @Operation(summary = "게시판 조회", description = "게시판을 조회합니다.")
    @GetMapping("/api/board")
    public List<BoardDto> boards() {
        List<Board> findBoards = boardService.findAllBoards();
        List<BoardDto> result = findBoards.stream()
                .map(b -> new BoardDto(b))
                .collect(Collectors.toList());

        return result;
    }

    //게시판 등록
    @Operation(summary = "게시판 등록", description = "게시판을 등록합니다.")
    @PostMapping("/api/board")
    public CreateBoardResponseDto createUser(@RequestBody @Valid CreateBoardRequestDto request) {
        Board board = Board.createBoard(request.getName());

        Long boardId = boardService.saveBoard(board);

        return new CreateBoardResponseDto(boardId);
    }

    //게시판 이름 수정
    @Operation(summary = "게시판 수정", description = "게시판의 이름을 조회합니다.")
    @PostMapping("/api/board/{id}")
    public UpdateBoardResponseDto updateBoard(@PathVariable("id") Long id,
                                              @RequestBody @Valid UpdateBoardRequestDto request) {
        boardService.updateName(id, request.getName());
        Board findBoard = boardService.findOneBoard(id);

        return new UpdateBoardResponseDto(findBoard.getId(), findBoard.getName());
    }

    //게시판 삭제
    @Operation(summary = "게시판 삭제", description = "게시판을 삭제합니다.")
    @DeleteMapping("/api/board/{id}")
    public DeleteBoardResponseDto deleteBoard(@PathVariable("id") Long id) {
        Board findBoard = boardService.findOneBoard(id);
        boardService.deleteBoard(findBoard);

        return new DeleteBoardResponseDto(id, findBoard.getName());
    }



}
