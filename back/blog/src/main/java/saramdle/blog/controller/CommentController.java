package saramdle.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saramdle.blog.domain.Comment;
import saramdle.blog.domain.CommentRequestDto;
import saramdle.blog.service.CommentService;
import saramdle.blog.service.PostService;

import java.net.URI;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> postComment(@RequestBody CommentRequestDto commentRequestDto) {
        Comment comment = toEntity(commentRequestDto);

        Long commentId = commentService.save(comment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commentId)
                .toUri();

        return ResponseEntity.created(location).body("댓글 작성이 완료되었습니다.");
    }
    
    private Comment toEntity(CommentRequestDto commentRequestDto) {
        return Comment.builder()
                .contents(commentRequestDto.getContents())
                .author(commentRequestDto.getAuthor())
                .post(postService.findPost(commentRequestDto.getPostId()))
                .build();
    }
}
