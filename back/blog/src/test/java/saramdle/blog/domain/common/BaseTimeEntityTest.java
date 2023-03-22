package saramdle.blog.domain.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import saramdle.blog.domain.Comment;
import saramdle.blog.domain.Post;
import saramdle.blog.service.CommentService;
import saramdle.blog.service.PostService;

@SpringBootTest
class BaseTimeEntityTest {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("게시물을 저장하면 생성, 수정시간이 자동으로 생성된다.")
    void generateTimeOfPost() {
        Post post = Post.builder().build();
        Long postId = postService.save(post);

        Post findPost = postService.findPost(postId);

        assertThat(findPost.getCreatedAt()).isNotNull();
        assertThat(findPost.getUpdatedAt()).isNotNull();
    }

    @Test
    @DisplayName("댓글을 저장하면 생성, 수정시간이 자동으로 생성된다.")
    void generateTimeOfComment() {
        Comment comment = Comment.builder().build();
        Long commentId = commentService.save(comment);

        Comment findComment = commentService.findComment(commentId);

        assertThat(findComment.getCreatedAt()).isNotNull();
        assertThat(findComment.getUpdatedAt()).isNotNull();
    }
}
