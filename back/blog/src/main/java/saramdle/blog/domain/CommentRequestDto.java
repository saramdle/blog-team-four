package saramdle.blog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {
    private String contents;
    private User user;
    private Long postId;

    @Builder
    private CommentRequestDto(String contents, User user, Long postId) {
        this.contents = contents;
        this.user = user;
        this.postId = postId;
    }
}
