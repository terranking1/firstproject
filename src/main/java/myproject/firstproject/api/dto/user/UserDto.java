package myproject.firstproject.api.dto.user;

import lombok.Getter;
import myproject.firstproject.domain.User;

@Getter
public class UserDto {
    private Long id;
    private String name;
    private String nickname;

    public UserDto(User user) {
        id = user.getId();
        name = user.getName();
        nickname = user.getNickname();
    }
}
