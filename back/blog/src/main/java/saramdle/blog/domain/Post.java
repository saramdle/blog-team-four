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

    private String content;

    private String author;

    private String imgUrl;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @Builder
    private Post(String title, String content, String author, String imgUrl,
                 LocalDateTime createAt, LocalDateTime updateAt) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.imgUrl = imgUrl;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public void update(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
