package myproject.firstproject.api.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteBoardResponseDto {
    private Long id;
    private String name;
}
