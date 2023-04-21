package myproject.firstproject.api.dto.post;

import lombok.Getter;

@Getter
public class CreatePostRequestDto {
        private Long userId;
        private Long boardId;
        private String title;
        private String contents;
}
