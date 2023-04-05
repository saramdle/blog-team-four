package saramdle.blog.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saramdle.blog.domain.Post;
import saramdle.blog.domain.PostRepository;
import saramdle.blog.domain.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final String NOT_FOUND_POST = "ID[%s] 게시물을 찾을 수 없습니다.";

    private final PostRepository repository;

    @Transactional
    public Long save(Post post) {
        return repository.save(post).getId();
    }

    @Transactional(readOnly = true)
    public Post findPost(Long postId) {
        return repository.findById(postId)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_POST, postId)));
    }

    @Transactional(readOnly = true)
    public List<Post> findPosts() {
        return repository.findAll();
    }

    @Transactional
    public Long update(Long postId, Post updateParam) {
        Post post = findPost(postId);
        post.update(updateParam.getTitle(), updateParam.getContents(), updateParam.getImgUrl());
        return postId;
    }

    @Transactional
    public void delete(Long postId) {
        Post post = findPost(postId);
        repository.delete(post);
    }
}
