package saramdle.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import saramdle.blog.config.auth.CustomUserPrinciple;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping
    public ResponseEntity home(@AuthenticationPrincipal CustomUserPrinciple user) {
        if (user == null) {
             return ResponseEntity.ok("비로그인 요청");
        }
        return ResponseEntity.ok(user.getUser());
    }
}
