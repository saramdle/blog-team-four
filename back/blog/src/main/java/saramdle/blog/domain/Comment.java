package saramdle.blog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private String author;

    private String imgUrl;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @Builder
    private Comment(Long id, String title, String contents, String author, String imgUrl,
                    LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.imgUrl = imgUrl;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
