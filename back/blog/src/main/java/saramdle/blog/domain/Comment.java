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
    
    private String contents;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String author;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @Builder
    private Comment(Long id, String contents, Post post, String author,
                    LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.contents = contents;
        this.post = post;
        this.author = author;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
