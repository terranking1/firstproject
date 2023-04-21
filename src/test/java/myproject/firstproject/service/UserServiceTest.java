package myproject.firstproject.service;

import myproject.firstproject.domain.User;
import myproject.firstproject.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        //given
        User user = new User();
        user.setName("박정균");
        user.setNickname("terranking");
        //when
        Long saveId = userService.join(user);
        //then
        Assertions.assertEquals(user, userRepository.findOne(saveId));
     }

}