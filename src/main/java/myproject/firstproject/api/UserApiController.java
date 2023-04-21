package myproject.firstproject.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import myproject.firstproject.domain.User;
import myproject.firstproject.repository.UserRepository;
import myproject.firstproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    //회원 등록
    @PostMapping("/api/users")
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
        User user = User.createUser(request.getName(), request.getNickname());

        Long userId = userService.join(user);

        return new CreateUserResponse(userId);
    }

    //회원 닉네임 수정
    @PostMapping("/api/users/{id}/nickname")
    public UpdateUserResponse updateUserNickname(@PathVariable("id") Long id,
                                                 @RequestBody @Valid UpdateUserRequest request) {
        userService.updateNickname(id, request.getNickname());
        User findUser = userService.findOneUser(id);

        return new UpdateUserResponse(findUser.getId(), findUser.getNickname());
    }

    //회원 삭제
    @DeleteMapping("/api/users/{id}")
    public DeleteUserResponse deleteUser(@PathVariable("id") Long id) {
        User findUser = userService.findOneUser(id);
        userService.deleteUser(findUser);

        return new DeleteUserResponse(id, findUser.getName());
    }

    //회원 조회
    @GetMapping("/api/users")
    public List<UserDto> users() {
        List<User> findUsers = userService.findAllUsers();
        List<UserDto> result = findUsers.stream()
                .map(u -> new UserDto(u))
                .collect(Collectors.toList());

        return result;
    }

    @Getter
    @AllArgsConstructor
    static class UserDto {
        private Long id;
        private String name;
        private String nickname;

        public UserDto(User user) {
            id = user.getId();
            name = user.getName();
            nickname = user.getNickname();
        }
    }

    @Getter
    static class CreateUserRequest {
        private String name;
        private String nickname;
    }

    @Getter
    @AllArgsConstructor
    static class CreateUserResponse {
        private Long id;
    }

    @Getter
    static class UpdateUserRequest {
        private String name;
        private String nickname;
    }

    @Getter
    @AllArgsConstructor
    static class UpdateUserResponse {
        private Long id;
        private String nickname;
    }

    @Getter
    @AllArgsConstructor
    static class DeleteUserResponse {
        private Long id;
        private String name;
    }
}
