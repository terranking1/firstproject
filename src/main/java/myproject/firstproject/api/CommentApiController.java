package myproject.firstproject.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import myproject.firstproject.api.dto.comment.*;
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

    //댓글 조회
    @GetMapping("/api/comment")
    public List<CommentDto> comments(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                     @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Comment> findComments = commentRepository.findAllWithUserPost(offset, limit);
        List<CommentDto> result = findComments.stream()
                .map(c -> new CommentDto(c))
                .collect(Collectors.toList());

        return result;
    }

    //댓글 작성
    @PostMapping("/api/comment")
    public CreateCommentResponseDto createComment(@RequestBody @Valid CreateCommentRequestDto request) {
        Long commentId = commentService.saveComment(request.getUserId(), request.getPostId(), request.getContents());

        return new CreateCommentResponseDto(commentId);
    }

    //댓글 수정
    @PostMapping("/api/comment/{id}")
    public UpdateCommentResponseDto updateComment(@PathVariable("id") Long id,
                                                  @RequestBody @Valid UpdateCommentRequestDto request) {
        commentService.updateComment(id, request.getContents());
        Comment findComment = commentService.findOneComment(id);

        return new UpdateCommentResponseDto(findComment.getId(), findComment.getContents());
    }

    //댓글 삭제
    @DeleteMapping("/api/comment/{id}")
    public DeleteCommentResponseDto deleteComment(@PathVariable("id") Long id) {
        Comment findComment = commentService.findOneComment(id);
        commentService.deleteComment(findComment);

        return new DeleteCommentResponseDto(findComment.getId());
    }


}

