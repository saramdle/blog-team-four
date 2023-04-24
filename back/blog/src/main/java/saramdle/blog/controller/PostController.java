package saramdle.blog.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saramdle.blog.config.auth.CustomUserPrinciple;
import saramdle.blog.domain.Post;
import saramdle.blog.domain.PostRequestDto;
import saramdle.blog.domain.PostResponseDto;
import saramdle.blog.domain.User;
import saramdle.blog.service.PostService;
import saramdle.blog.service.UserService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> newPost(@RequestBody PostRequestDto postRequestDto,
                                          @AuthenticationPrincipal CustomUserPrinciple userPrinciple) {
        User findUser = userService.findUser(userPrinciple.getUserEmail());
        Post post = toEntity(postRequestDto, findUser);
        Long postId = postService.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(postId)
                .toUri();

        return ResponseEntity.created(location).body("게시물 등록 성공");
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> list() {
        List<Post> posts = postService.findPosts();

        List<PostResponseDto> dtos = posts.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> detail(@PathVariable long id) {
        Post post = postService.findPost(id);
        PostResponseDto dto = toDto(post);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> update(@PathVariable long id, @RequestBody PostRequestDto postRequestDto) {
        Long postId = postService.update(id, toEntity(postRequestDto));
        Post post = postService.findPost(postId);
        return ResponseEntity.ok(toDto(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        postService.delete(id);
        return ResponseEntity.ok("게시물 삭제 성공");
    }

    private PostResponseDto toDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .contents(post.getContents())
                .user(post.getUser())
                .imgUrl(post.getImgUrl())
                .createdAt(post.getCreatedAt())
                .build();
    }

    private Post toEntity(PostRequestDto postRequestDto) {
        return Post.builder()
                .title(postRequestDto.getTitle())
                .contents(postRequestDto.getContents())
                .imgUrl(postRequestDto.getImgUrl())
                .build();
    }

    private Post toEntity(PostRequestDto postRequestDto, User user) {
        return Post.builder()
                .title(postRequestDto.getTitle())
                .contents(postRequestDto.getContents())
                .imgUrl(postRequestDto.getImgUrl())
                .user(user)
                .build();
    }
}
