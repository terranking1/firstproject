package myproject.firstproject.api.dto.comment;

import lombok.Getter;
import myproject.firstproject.domain.Comment;
import myproject.firstproject.domain.User;

import java.time.LocalDateTime;

@Getter
public class CommentDto {
    private Long commentId;
    private Long postId;
    private String userNickname;
    private LocalDateTime commentRegDate;

    public CommentDto(Comment comment) {
        commentId = comment.getId();
        postId = comment.getPost().getId();
        userNickname = comment.getUser().getNickname();
        commentRegDate = comment.getRegDate();
    }
}
