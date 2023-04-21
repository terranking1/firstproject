package myproject.firstproject.api.dto.post;

import lombok.Getter;
import myproject.firstproject.api.CommentApiController;
import myproject.firstproject.api.dto.comment.CommentDto;
import myproject.firstproject.domain.Post;
import myproject.firstproject.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostDto {
    private Long postId;
    private String boardName;
    private String userNickname;
    private String title;
    private String contents;
    private LocalDateTime postRegDate;
    private List<CommentDto> comments = new ArrayList<>();

    public PostDto(Post post) {
        postId = post.getId();
        boardName = post.getBoard().getName();
        userNickname = post.getUser().getNickname();
        title = post.getTitle();
        contents = post.getContents();
        postRegDate = post.getRegDate();
        comments = post.getComments().stream()
                .map(Comment -> new CommentDto(Comment))
                .collect(Collectors.toList());
    }
}
