package saramdle.blog.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import saramdle.blog.domain.Comment;
import saramdle.blog.domain.Post;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @BeforeEach
    void beforeEach() {
        Post post1 = Post.builder().build();
        Long savedId1 = postService.save(post1);
        Assertions.assertThat(post1.getId()).isEqualTo(savedId1);
    }

    @DisplayName("댓글 저장")
    @Test
    void save() {
        Post post = Post.builder().build();
        postService.save(post);

        Comment comment = Comment.builder().post(post).build();
        Long saveId = commentService.save(comment);

        assertThat(saveId).isEqualTo(comment.getId());
    }

    @Test
    void findComment() {
        Post post = Post.builder().build();
        postService.save(post);

        Comment comment = Comment.builder().post(post).build();
        Long saveId = commentService.save(comment);

        Comment findedComment = commentService.findComment(saveId);

        assertThat(saveId).isEqualTo(findedComment.getId());
    }

    @Test
    void findComments() {
        Post post = Post.builder().build();
        postService.save(post);

        Comment comment = Comment.builder().post(post).build();

        commentService.save(comment);

        List<Comment> findedComments = commentService.findComments(comment.getPost().getId());

        assertThat(findedComments.size()).isEqualTo(1);
    }
}
