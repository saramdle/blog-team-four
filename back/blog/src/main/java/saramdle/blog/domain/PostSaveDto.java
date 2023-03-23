package saramdle.blog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시물 저장 요청
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSaveDto {
    private String title;
    private String contents;
    private String author;
    private String imgUrl;

    @Builder
    private PostSaveDto(String title, String contents, String author, String imgUrl) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.imgUrl = imgUrl;
    }
}
