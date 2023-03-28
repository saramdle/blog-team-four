package saramdle.blog.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import saramdle.blog.domain.Comment;
import saramdle.blog.domain.Post;
import saramdle.blog.domain.exception.NotFoundException;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;


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

        List<Comment> foundComments = commentService.findComments(post.getId());
        assertThat(foundComments.size()).isEqualTo(1);
    }

    @Test
    void updateComments() {
        Post post = Post.builder().build();
        postService.save(post);

        Comment comment = Comment.builder().post(post).build();
        Long commentId = commentService.save(comment);

        Comment newComment = Comment.builder().contents("Hello World").build();

        Long updatedCommentId = commentService.updateComment(commentId, newComment);

        Comment result = commentService.findComment(updatedCommentId);

        assertThat(result.getContents()).isEqualTo("Hello World");
    }

    @Test
    void deleteComment() {
        Post post = Post.builder().build();
        postService.save(post);

        Comment comment = Comment.builder().post(post).build();
        Long commentId = commentService.save(comment);

        commentService.deleteComment(commentId);

        assertThatThrownBy(() -> commentService.findComment(commentId))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("댓글을 찾을 수 없습니다.");
    }
}
