package saramdle.blog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequestDto {
    private String title;
    private String contents;
    private User user;
    private String imgUrl;

    @Builder
    private PostRequestDto(String title, String contents, User user, String imgUrl) {
        this.title = title;
        this.contents = contents;
        this.user = user;
        this.imgUrl = imgUrl;
    }
}
