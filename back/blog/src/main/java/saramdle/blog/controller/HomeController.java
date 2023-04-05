package saramdle.blog.controller;

import static saramdle.blog.config.auth.SessionConst.LOGIN_USER;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import saramdle.blog.config.auth.dto.SessionUser;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeController {
    
    private final HttpSession session;
    
    @GetMapping
    public ResponseEntity home() {
        SessionUser sessionUser = (SessionUser) session.getAttribute(LOGIN_USER);

        if (sessionUser == null) {
             return ResponseEntity.ok("비로그인 요청");
        }

        return ResponseEntity.ok(sessionUser);
    }
}
