package myproject.firstproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "comment_contents")
    private String contents;

    @Column(name = "comment_regDate")
    private LocalDateTime regDate;

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }

    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    //==생성 메서드==//
    public static Comment createComment(User user, Post post, String contents) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContents(contents);
        comment.setRegDate(LocalDateTime.now());

        return comment;
    }

    public void changeContents(String contents) {
        this.contents = contents;
    }
}
