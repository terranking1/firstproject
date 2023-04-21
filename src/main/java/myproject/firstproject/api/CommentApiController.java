package myproject.firstproject.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import myproject.firstproject.domain.Comment;
import myproject.firstproject.repository.CommentRepository;
import myproject.firstproject.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    //댓글 작성
    @PostMapping("/api/comments")
    public CreateCommentResponse createComment(@RequestBody @Valid CreateCommentRequest request) {
        Long commentId = commentService.saveComment(request.getUserId(), request.getPostId(), request.getContents());

        return new CreateCommentResponse(commentId);
    }

    //댓글 수정
    @PostMapping("/api/comments/{id}")
    public UpdateCommentResponse updateComment(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateCommentRequest request) {
        commentService.updateComment(id, request.getContents());
        Comment findComment = commentService.findOneComment(id);

        return new UpdateCommentResponse(findComment.getId(), findComment.getContents());
    }

    //댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public DeleteCommentResponse deleteComment(@PathVariable("id") Long id) {
        Comment findComment = commentService.findOneComment(id);
        commentService.deleteComment(findComment);

        return new DeleteCommentResponse(findComment.getId());
    }

    //댓글 조회
    @GetMapping("/api/comments")
    public List<CommentDto> comments(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                     @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Comment> findComments = commentRepository.findAllWithUserPost(offset, limit);
        List<CommentDto> result = findComments.stream()
                .map(c -> new CommentDto(c))
                .collect(Collectors.toList());

        return result;
    }

    @Getter
    @AllArgsConstructor
    static class CommentDto {
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

    @Getter
    static class CreateCommentRequest {
        private Long userId;
        private Long postId;
        private String contents;
    }

    @Getter
    @AllArgsConstructor
    static class CreateCommentResponse {
        private Long id;
    }

    @Getter
    static class UpdateCommentRequest {
        private String contents;
    }

    @Getter
    @AllArgsConstructor
    static class UpdateCommentResponse {
        private Long id;
        private String contents;
    }

    @Getter
    @AllArgsConstructor
    static class DeleteCommentResponse {
        private Long id;
    }
}

