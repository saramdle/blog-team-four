package saramdle.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import saramdle.blog.service.S3UploaderService;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/image")
@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    private final S3UploaderService s3UploaderService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String result = s3UploaderService.upload(file, "upload");
        return ResponseEntity.ok(result);
    }
}
