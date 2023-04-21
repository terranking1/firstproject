package myproject.firstproject.api.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateCommentResponseDto {
    private Long id;
    private String contents;
}
