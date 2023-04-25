package saramdle.blog.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {
    private Long id;
    private String contents;
    private String author;
    private LocalDateTime createdAt;

    @Builder
    private CommentResponseDto(Long id, String contents, String author, LocalDateTime createdAt) {
        this.id = id;
        this.contents = contents;
        this.author = author;
        this.createdAt = createdAt;
    }
}
