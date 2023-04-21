package myproject.firstproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_nickname")
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public static User createUser(String name, String nickname) {
        User user = new User();
        user.setName(name);
        user.setNickname(nickname);

        return user;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
