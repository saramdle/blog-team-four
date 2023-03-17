package saramdle.blog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import saramdle.blog.domain.Post;
import saramdle.blog.domain.PostRepository;

@Transactional
@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("게시물을 저장합니다.")
    void save() {
        Post post = Post.builder().build();
        Long savedId = postService.save(post);
        assertThat(savedId).isEqualTo(post.getId());
    }

    @Test
    @DisplayName("하나의 게시물을 조회합니다.")
    void findPost() {
        Post post = Post.builder().build();
        postRepository.save(post);

        Post findPost = postService.findPost(post.getId());

        assertThat(findPost).isEqualTo(post);
    }

    @Test
    @DisplayName("존재하지 않은 게시물을 조회할 경우 예외가 발생합니다.")
    void findPostFail() {
        assertThatThrownBy(() -> postService.findPost(100L))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("모든 게시물을 조회합니다.")
    void findPosts() {
        Post post1 = Post.builder().build();
        Post post2 = Post.builder().build();
        postRepository.save(post1);
        postRepository.save(post2);

        List<Post> posts = postService.findPosts();

        assertThat(posts).hasSize(2);
    }
}
