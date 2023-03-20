package saramdle.blog.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import saramdle.blog.domain.Comment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentService commentService;

    @DisplayName("댓글 저장")
    @Test
    void save() {
        Comment comment = Comment.builder().build();
        Long saveId = commentService.save(comment);
        assertThat(saveId).isEqualTo(comment.getId());
    }

}