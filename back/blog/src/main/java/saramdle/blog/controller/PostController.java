package saramdle.blog.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saramdle.blog.domain.Post;
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

    private Post toEntity(PostSaveDto postSaveDto) {
        return Post.builder()
                .title(postSaveDto.getTitle())
                .contents(postSaveDto.getContents())
                .author(postSaveDto.getAuthor())
                .imgUrl(postSaveDto.getImgUrl())
                .build();
    }
}
