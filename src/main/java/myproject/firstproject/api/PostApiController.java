package myproject.firstproject.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import myproject.firstproject.domain.Board;
import myproject.firstproject.domain.Comment;
import myproject.firstproject.domain.Post;
import myproject.firstproject.domain.User;
import myproject.firstproject.repository.PostRepository;
import myproject.firstproject.repository.UserRepository;
import myproject.firstproject.service.BoardService;
import myproject.firstproject.service.PostService;
import myproject.firstproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final PostRepository postRepository;

    //게시글 등록
    @PostMapping("/api/posts")
    public CreatePostResponse createPost(@RequestBody @Valid CreatePostRequest request) {
        Long postId = postService.savePost(request.getUserId(), request.getBoardId(), request.getTitle(), request.getContents());

        return new CreatePostResponse(postId);
    }

    //게시글 제목, 내용 수정
    @PostMapping("/api/posts/{id}")
    public UpdatePostResponse updatePost(@PathVariable("id") Long id,
                                         @RequestBody @Valid UpdatePostRequest request) {
        postService.updatePost(id, request.getTitle(), request.getContents());
        Post findPost = postService.findOnePost(id);

        return new UpdatePostResponse(findPost.getId(), findPost.getTitle(), findPost.getContents());
    }

    //게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public DeletePostResponse deletePost(@PathVariable("id") Long id) {
        Post findPost = postService.findOnePost(id);
        postService.deletePost(findPost);

        return new DeletePostResponse(findPost.getId());
    }

    //게시글 조회
    @GetMapping("/api/posts")
    public List<PostDto> posts(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Post> findPosts = postRepository.findAllWithUserBoard(offset, limit);
        List<PostDto> result = findPosts.stream()
                .map(p -> new PostDto(p))
                .collect(Collectors.toList());

        return result;
    }

    @Getter
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Getter
    @AllArgsConstructor
    static class PostDto {
        private Long postId;
        private String boardName;
        private String userNickname;
        private String title;
        private String contents;
        private LocalDateTime postRegDate;
        private List<CommentApiController.CommentDto> comments = new ArrayList<>();

        public PostDto(Post post) {
            postId = post.getId();
            boardName = post.getBoard().getName();
            userNickname = post.getUser().getNickname();
            title = post.getTitle();
            contents = post.getContents();
            postRegDate = post.getRegDate();
            comments = post.getComments().stream()
                    .map(Comment -> new CommentApiController.CommentDto(Comment))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @AllArgsConstructor
    static class CommentDto {

        private String userNickname;
        private String contents;

        public CommentDto(Comment comment) {
            userNickname = comment.getUser().getNickname();
            contents = comment.getContents();
        }
    }


    @Getter
    static class CreatePostRequest {
        private Long userId;
        private Long boardId;
        private String title;
        private String contents;
    }

    @Getter
    @AllArgsConstructor
    static class CreatePostResponse {
        private Long id;
    }

    @Getter
    static class UpdatePostRequest {
        private String title;
        private String contents;
    }

    @Getter
    @AllArgsConstructor
    static class UpdatePostResponse {
        private Long id;
        private String title;
        private String contents;
    }

    @Getter
    @AllArgsConstructor
    static class DeletePostResponse {
        private Long id;
    }
}
