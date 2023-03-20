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

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String author;

    private String imgUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    private Comment(Long id, String title, String contents, Post post, String author, String imgUrl,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.post = post;
        this.author = author;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
