package saramdle.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saramdle.blog.domain.Post;
import saramdle.blog.domain.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    @Transactional
    public Long save(Post post) {
        return repository.save(post).getId();
    }
}
