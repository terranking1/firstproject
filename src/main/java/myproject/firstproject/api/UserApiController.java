package myproject.firstproject.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import myproject.firstproject.api.dto.user.*;
import myproject.firstproject.domain.User;
import myproject.firstproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "user", description = "회원 API")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    //회원 조회
    @Operation(summary = "회원 조회", description = "회원을 조회합니다.")
    @GetMapping("/user")
    public List<UserDto> users() {
        List<User> findUsers = userService.findAllUsers();
        List<UserDto> result = findUsers.stream()
                .map(u -> new UserDto(u))
                .collect(Collectors.toList());

        return result;
    }

    //회원 등록
    @Operation(summary = "회원 등록", description = "회원을 등록합니다.")
    @PostMapping("/user")
    public CreateUserResponseDto createUser(@RequestBody @Valid CreateUserRequestDto request) {
        User user = User.createUser(request.getName(), request.getNickname());

        Long userId = userService.join(user);

        return new CreateUserResponseDto(userId);
    }

    //회원 닉네임 수정
    @Operation(summary = "회원 수정", description = "회원의 닉네임을 수정합니다.")
    @PostMapping("/user/{id}/nickname")
    public UpdateUserResponseDto updateUserNickname(@PathVariable("id") Long id,
                                                    @RequestBody @Valid UpdateUserRequestDto request) {
        userService.updateNickname(id, request.getNickname());
        User findUser = userService.findOneUser(id);

        return new UpdateUserResponseDto(findUser.getId(), findUser.getNickname());
    }

    //회원 삭제
    @Operation(summary = "회원 삭제", description = "회원을 삭제합니다.")
    @DeleteMapping("/user/{id}")
    public DeleteUserResponseDto deleteUser(@PathVariable("id") Long id) {
        User findUser = userService.findOneUser(id);
        userService.deleteUser(findUser);

        return new DeleteUserResponseDto(id, findUser.getName());
    }

}
