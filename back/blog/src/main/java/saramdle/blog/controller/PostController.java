package saramdle.blog.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saramdle.blog.domain.Post;
import saramdle.blog.domain.PostResponseDto;
import saramdle.blog.domain.PostSaveDto;
import saramdle.blog.service.PostService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> newPost(@RequestBody PostSaveDto postSaveDto) {
        Post post = toEntity(postSaveDto);
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

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<PostResponseDto> detail(@PathVariable long id) {
        Post post = postService.findPost(id);
        PostResponseDto dto = toDto(post);
        return ResponseEntity.ok(dto);
    }

    private PostResponseDto toDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .contents(post.getContents())
                .author(post.getAuthor())
                .imgUrl(post.getImgUrl())
                .createdAt(post.getCreatedAt())
                .build();
    }

    private Post toEntity(PostSaveDto postSaveDto) {
        return Post.builder()
                .title(postSaveDto.getTitle())
                .contents(postSaveDto.getContents())
                .author(postSaveDto.getAuthor())
                .imgUrl(postSaveDto.getImgUrl())
                .build();
    }
}
