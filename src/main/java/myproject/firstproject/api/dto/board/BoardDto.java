package myproject.firstproject.api.dto.board;

import lombok.Getter;
import myproject.firstproject.domain.Board;
import myproject.firstproject.domain.User;

@Getter
public class BoardDto {
    private Long id;
    private String name;

    public BoardDto(Board board) {
        id = board.getId();
        name = board.getName();
    }
}
