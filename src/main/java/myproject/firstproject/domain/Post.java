package myproject.firstproject.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_contents")
    private String contents;

    @Column(name = "post_regDate")
    private LocalDateTime regDate;

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    public void setBoard(Board board) {
        this.board = board;
        board.getPosts().add(this);
    }

    //==생성 메서드==//
    public static Post createPost(User user, Board board, String title, String contents) {
        Post post = new Post();
        post.setUser(user);
        post.setBoard(board);
        post.setTitle(title);
        post.setContents(contents);
        post.setRegDate(LocalDateTime.now());

        return post;
    }

    public void changePost(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }



}
