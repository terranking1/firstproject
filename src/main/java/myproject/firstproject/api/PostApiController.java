package myproject.firstproject.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import myproject.firstproject.api.dto.post.*;
import myproject.firstproject.domain.Comment;
import myproject.firstproject.domain.Post;
import myproject.firstproject.repository.PostRepository;
import myproject.firstproject.service.PostService;
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

    //게시글 조회
    @GetMapping("/api/post")
    public List<PostDto> posts(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Post> findPosts = postRepository.findAllWithUserBoard(offset, limit);
        List<PostDto> result = findPosts.stream()
                .map(p -> new PostDto(p))
                .collect(Collectors.toList());

        return result;
    }

    //게시글 등록
    @PostMapping("/api/post")
    public CreatePostResponseDto createPost(@RequestBody @Valid CreatePostRequestDto request) {
        Long postId = postService.savePost(request.getUserId(), request.getBoardId(), request.getTitle(), request.getContents());

        return new CreatePostResponseDto(postId);
    }

    //게시글 제목, 내용 수정
    @PostMapping("/api/post/{id}")
    public UpdatePostResponseDto updatePost(@PathVariable("id") Long id,
                                            @RequestBody @Valid UpdatePostRequestDto request) {
        postService.updatePost(id, request.getTitle(), request.getContents());
        Post findPost = postService.findOnePost(id);

        return new UpdatePostResponseDto(findPost.getId(), findPost.getTitle(), findPost.getContents());
    }

    //게시글 삭제
    @DeleteMapping("/api/post/{id}")
    public DeletePostResponseDto deletePost(@PathVariable("id") Long id) {
        Post findPost = postService.findOnePost(id);
        postService.deletePost(findPost);

        return new DeletePostResponseDto(findPost.getId());
    }


}
