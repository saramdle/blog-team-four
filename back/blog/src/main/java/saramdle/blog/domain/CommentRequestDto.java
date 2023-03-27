package saramdle.blog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import saramdle.blog.service.PostService;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {
    private String contents;
    private String author;
    private Long postId;

    @Builder
    private CommentRequestDto(String contents, String author, Long postId) {
        this.contents = contents;
        this.author = author;
        this.postId = postId;
    }
}
