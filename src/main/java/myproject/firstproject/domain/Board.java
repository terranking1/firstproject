package myproject.firstproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @OneToMany(mappedBy = "board")
    private List<Post> posts;

    @Column(name = "board_name")
    private String name;

    public static Board createBoard(String name) {
        Board board = new Board();
        board.setName(name);

        return board;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
