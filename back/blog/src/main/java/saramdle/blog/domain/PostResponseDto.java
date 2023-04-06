package saramdle.blog.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    private User user;
    private String imgUrl;
    private LocalDateTime createdAt;

    @Builder
    private PostResponseDto(Long id, String title, String contents, User user,
                            String imgUrl, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user = user;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
    }
}
