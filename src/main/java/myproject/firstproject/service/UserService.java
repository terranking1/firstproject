package myproject.firstproject.service;

import lombok.RequiredArgsConstructor;
import myproject.firstproject.domain.User;
import myproject.firstproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 등록
     */
    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);

        return user.getId();
    }

    /**
     * 회원 닉네임 수정
     */
    @Transactional
    public void updateNickname(Long userId, String nickname) {
        User findUser = userRepository.findOne(userId);
        findUser.changeNickname(nickname);
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    /**
     * 회원 조회
     */
    //특정 회원 조회
    public User findOneUser(Long userId) {
        return userRepository.findOne(userId);
    }

    //전체 회원 조회
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 예외 처리
     */
    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByName(user.getName());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
