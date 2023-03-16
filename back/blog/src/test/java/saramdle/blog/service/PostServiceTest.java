package saramdle.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import saramdle.blog.domain.Post;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    @DisplayName("게시물을 저장합니다.")
    void save() {
        Post post = Post.builder().build();
        Long savedId = postService.save(post);
        assertThat(savedId).isEqualTo(post.getId());
    }
}
