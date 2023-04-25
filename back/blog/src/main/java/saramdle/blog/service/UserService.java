package saramdle.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saramdle.blog.domain.User;
import saramdle.blog.domain.UserRepository;
import saramdle.blog.domain.exception.NotFoundException;

@RequiredArgsConstructor
@Service
public class UserService {

    private static final String NOT_FOUND_USER = "EMAIL[%s] 회원을 찾을 수 없습니다.";

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_USER, email)));
    }
}
