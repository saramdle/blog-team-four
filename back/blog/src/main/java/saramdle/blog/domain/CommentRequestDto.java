package saramdle.blog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {
    private String contents;
    private Long postId;

    @Builder
    private CommentRequestDto(String contents, Long postId) {
        this.contents = contents;
        this.postId = postId;
    }
}
