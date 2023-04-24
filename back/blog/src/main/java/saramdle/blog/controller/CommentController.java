package saramdle.blog.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saramdle.blog.config.auth.CustomUserPrinciple;
import saramdle.blog.domain.Comment;
import saramdle.blog.domain.CommentRequestDto;
import saramdle.blog.domain.CommentResponseDto;
import saramdle.blog.domain.User;
import saramdle.blog.service.CommentService;
import saramdle.blog.service.PostService;
import saramdle.blog.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;

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
    public ResponseEntity<String> postComment(@RequestBody CommentRequestDto commentRequestDto,
                                              @AuthenticationPrincipal CustomUserPrinciple userPrinciple) {
        User user = userService.findUser(userPrinciple.getUserEmail());
        Comment comment = toEntity(commentRequestDto, user);

        Long commentId = commentService.save(comment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commentId)
                .toUri();

        return ResponseEntity.created(location).body("댓글 작성이 완료되었습니다.");
    }

    @PutMapping("/{commentId}")
    @ResponseBody
    public ResponseEntity<CommentResponseDto> updateComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long commentId) {
        commentId = commentService.updateComment(commentId, toEntity(commentRequestDto));
        Comment comment = commentService.findComment(commentId);
        return ResponseEntity.ok(toDto(comment));
    }

    @DeleteMapping("/{commentId}")
    @ResponseBody
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글 삭제 성공");
    }
    
    private Comment toEntity(CommentRequestDto commentRequestDto) {
        return Comment.builder()
                .contents(commentRequestDto.getContents())
                .post(postService.findPost(commentRequestDto.getPostId()))
                .build();
    }

    private Comment toEntity(CommentRequestDto commentRequestDto, User user) {
        return Comment.builder()
                .contents(commentRequestDto.getContents())
                .user(user)
                .post(postService.findPost(commentRequestDto.getPostId()))
                .build();
    }

    private CommentResponseDto toDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .contents(comment.getContents())
                .user(comment.getUser())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
