package myproject.firstproject.service;

import lombok.RequiredArgsConstructor;
import myproject.firstproject.domain.Comment;
import myproject.firstproject.domain.Post;
import myproject.firstproject.domain.User;
import myproject.firstproject.repository.CommentRepository;
import myproject.firstproject.repository.PostRepository;
import myproject.firstproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    /**
     * 댓글 등록
     */
    @Transactional
    public Long saveComment(Long userId, Long postId, String contents) {

        User user = userRepository.findOne(userId);
        Post post = postRepository.findOne(postId);

        Comment comment = Comment.createComment(user, post, contents);

        commentRepository.save(comment);

        return comment.getId();
    }

    /**
     * 댓글 수정
     */
    @Transactional
    public void updateComment(Long commentId, String contents) {
        Comment findComment = commentRepository.findOne(commentId);
        findComment.changeContents(contents);
    }

    /**
     * 댓글 삭제
     */
    @Transactional
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    /**
     * 댓글 조회
     */
    //틀정 댓글 조회
    public Comment findOneComment(Long id) {
        return commentRepository.findOne(id);
    }

    //전체 댓글 조회
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }
}
