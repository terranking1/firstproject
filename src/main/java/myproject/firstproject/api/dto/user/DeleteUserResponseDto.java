package myproject.firstproject.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteUserResponseDto {
    private Long id;
    private String name;
}
