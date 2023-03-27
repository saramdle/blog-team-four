package saramdle.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saramdle.blog.domain.Comment;
import saramdle.blog.domain.CommentRequestDto;
import saramdle.blog.domain.CommentResponseDto;
import saramdle.blog.service.CommentService;
import saramdle.blog.service.PostService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @GetMapping("/{postId}")
    @ResponseBody
    public ResponseEntity<List<CommentResponseDto>> getComment(@PathVariable Long postId) {
        List<Comment> comments = commentService.findComments(postId);
        List<CommentResponseDto> dtos = comments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

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

    private CommentResponseDto toDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .contents(comment.getContents())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
