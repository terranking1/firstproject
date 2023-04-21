package myproject.firstproject.api.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostResponseDto {
    private Long id;
    private String title;
    private String contents;
}
