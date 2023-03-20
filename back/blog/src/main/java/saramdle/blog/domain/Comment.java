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
    private Comment(Long id, String content, Post post, String commentWriter,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.post = post;
        this.commentWriter = commentWriter;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
