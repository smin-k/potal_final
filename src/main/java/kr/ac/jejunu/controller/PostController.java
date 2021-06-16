package kr.ac.jejunu.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import kr.ac.jejunu.dto.FileDto;
import kr.ac.jejunu.dto.PostDto;
import kr.ac.jejunu.repository.PostRepository;
import kr.ac.jejunu.service.FileService;
import kr.ac.jejunu.service.PostService;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private FileService fileService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    /*
     * 게시글 목록
     */
    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("postList", postService.findPostList(pageable));
        return "/post/list";
    }

    /*
     * 게시글 상세 및 등록 폼 호출
     */
    @GetMapping({"", "/"})
    public String post(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        model.addAttribute("post", postService.findPostById(id));
        return "/post/form";
    }

    /*
     * 게시글 생성
     */
    @PostMapping("/form")
    public  String postPost(@RequestParam("file2") MultipartFile file, @ModelAttribute PostDto postDto) throws IOException {
        String filename = file.getOriginalFilename();
        String savePath = System.getProperty("user.dir")+"/build/resources/main/static";
        /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
       if (!new File(savePath).exists()) {
            new File(savePath).mkdir();
       }
        new File(savePath).mkdir();
        String filePath = savePath + "\\" + filename;

        file.transferTo(new File(filePath));

        FileDto fileDto = new FileDto();
        fileDto.setFilename(filename);
        fileDto.setFilePath(filePath);


        postDto.setCreatedDate(LocalDateTime.now());
        postDto.setUpdatedDate(LocalDateTime.now());
        postDto.setFile(fileDto.toEntity());
        postRepository.save(postDto.toEntity());
        return "redirect:/post/list";
    }

    /*
     * 게시글 수정
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> putPost(@PathVariable("id") Long id, @RequestBody Post post) {
        Post updatePost = postRepository.getById(id);
        updatePost.setTitle(post.getTitle());
        updatePost.setContent(post.getContent());
        updatePost.setUpdatedDate(LocalDateTime.now());
        postRepository.save(updatePost);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    /*
     * 게시글 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
        FileDto fileDto = fileService.getFile(fileId);
        Path path = Paths.get(fileDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getFilename()+ "\"")
                .body(resource);
    }


}
