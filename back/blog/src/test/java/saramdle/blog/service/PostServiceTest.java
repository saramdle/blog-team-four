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
import saramdle.blog.domain.exception.NotFoundException;

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
                .isInstanceOf(NotFoundException.class);
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

    @Test
    @DisplayName("게시물을 수정합니다.")
    void update() {
        Post post = Post.builder()
                .title("제목1")
                .contents("본문1")
                .imgUrl("https://t1.daumcdn.net/cfile/tistory/993F75355A692ABD32")
                .build();
        Long postId = postRepository.save(post).getId();

        Post updateParam = Post.builder()
                .title("제목2")
                .contents("본문2")
                .imgUrl("https://img.siksinhot.com/article/1638761259231391.jpg")
                .build();
        postService.update(postId, updateParam);

        Post findPost = postRepository.findById(postId).orElseThrow();
        assertThat(findPost.getTitle()).isEqualTo(updateParam.getTitle());
        assertThat(findPost.getContents()).isEqualTo(updateParam.getContents());
        assertThat(findPost.getImgUrl()).isEqualTo(updateParam.getImgUrl());
    }

    @Test
    @DisplayName("게시물을 삭제합니다.")
    void delete() {
        Post post = Post.builder().build();
        Post savedPost = postRepository.save(post);
        Long postId = savedPost.getId();

        postService.delete(postId);

        List<Post> posts = postRepository.findAll();
        assertThat(posts).isEmpty();
    }
}
