package myproject.firstproject.service;

import lombok.RequiredArgsConstructor;
import myproject.firstproject.domain.Board;
import myproject.firstproject.domain.Post;
import myproject.firstproject.domain.User;
import myproject.firstproject.repository.BoardRepository;
import myproject.firstproject.repository.PostRepository;
import myproject.firstproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    /**
     * 게시글 등록
     */
    @Transactional
    public Long savePost(Long userId, Long boardId, String title, String contents) {

        User user = userRepository.findOne(userId);
        Board board = boardRepository.findOne(boardId);

        Post post = Post.createPost(user, board, title, contents);

        postRepository.save(post);

        return post.getId();
    }

    /**
     * 게시글 제목, 내용 수정
     */
    @Transactional
    public void updatePost(Long postId, String title, String contents) {
        Post findPost = postRepository.findOne(postId);
        findPost.changePost(title, contents);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    /**
     * 게시글 조회
     */
    //특정 게시글 조회(검색 조건)
    public Post findOnePost(Long id) {
        return postRepository.findOne(id);
    }

    //전체 게시글 조회
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }
}
