package saramdle.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saramdle.blog.domain.Post;
import saramdle.blog.domain.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final String NOT_FOUND_POST = "해당 게시물을 찾을 수 없습니다.";

    private final PostRepository repository;

    @Transactional
    public Long save(Post post) {
        return repository.save(post).getId();
    }

    @Transactional(readOnly = true)
    public Post findPost(Long postId) {
        return repository.findById(postId)
                .orElseThrow(() -> new IllegalStateException(NOT_FOUND_POST));
    }
}
