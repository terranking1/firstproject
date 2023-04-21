package myproject.firstproject.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserResponseDto {
    private Long id;
    private String nickname;
}
