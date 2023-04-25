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
    private String imgUrl;

    @Builder
    private PostRequestDto(String title, String contents, String imgUrl) {
        this.title = title;
        this.contents = contents;
        this.imgUrl = imgUrl;
    }
}
