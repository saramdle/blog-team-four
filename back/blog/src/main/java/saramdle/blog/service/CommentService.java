package saramdle.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saramdle.blog.domain.Comment;
import saramdle.blog.domain.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private static final String NOT_FOUND_COMMENT = "해당 댓글을 찾을 수 없습니다.";
    private final CommentRepository repository;

    @Transactional
    public Long save(Comment comment) {
        return repository.save(comment).getId();}

    @Transactional(readOnly = true)
    public Comment findComment(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalStateException(NOT_FOUND_COMMENT));
    }
}
