package myproject.firstproject.api.dto.comment;

import lombok.Getter;

@Getter
public class CreateCommentRequestDto {
        private Long userId;
        private Long postId;
        private String contents;
}
