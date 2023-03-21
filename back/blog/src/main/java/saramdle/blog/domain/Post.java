package saramdle.blog.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private String author;

    private String imgUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    private Post(String title, String contents, String author, String imgUrl,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(String title, String author, String contents) {
        this.title = title;
        this.author = author;
        this.contents = contents;
    }
}
