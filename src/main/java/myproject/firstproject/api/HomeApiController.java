package myproject.firstproject.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeApiController {

    @GetMapping("/home")
    public TestDto home() {
        TestDto result = new TestDto("park");

        return result;
    }

    @Getter
    static class TestDto {
        private String name;

        public TestDto(String name) {
            this.name = name;
        }
    }

}
