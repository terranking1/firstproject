package myproject.firstproject.controller;

import lombok.RequiredArgsConstructor;
import myproject.firstproject.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;


}
