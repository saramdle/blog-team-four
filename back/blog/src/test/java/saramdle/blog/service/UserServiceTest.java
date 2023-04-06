package saramdle.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import saramdle.blog.domain.User;
import saramdle.blog.domain.UserRepository;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @DisplayName("email로 회원을 조회합니다.")
    @Test
    void findUserByEmail() {
        User user = User.builder().email("test@gmail.com").build();
        User savedUser = userRepository.save(user);

        User findUser = userService.findUser(savedUser.getEmail());

        assertThat(findUser.getId()).isEqualTo(savedUser.getId());
    }
}
