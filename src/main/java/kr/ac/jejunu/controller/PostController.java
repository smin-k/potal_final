package kr.ac.jejunu.controller;


import kr.ac.jejunu.dto.FileDto;
import kr.ac.jejunu.dto.HomeworkDto;
import kr.ac.jejunu.dto.PostDto;
import kr.ac.jejunu.repository.HomeworkRepository;
import kr.ac.jejunu.repository.PostRepository;
import kr.ac.jejunu.service.FileService;
import kr.ac.jejunu.service.HomeworkService;
import kr.ac.jejunu.service.PostService;
import kr.ac.jejunu.service.UserService;
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

import java.io.IOException;

import java.security.Principal;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private FileService fileService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private HomeworkService homeworkService;

    /*
     * 게시글 목록
     */
    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("postList", postService.findPostList(pageable));
        return "/list";
    }

    /*
     * 게시글 상세 및 등록 폼 호출
     */
    @GetMapping("/form")
    public String post(@RequestParam(value = "id", defaultValue = "0") Long id, Model model, Principal principal) {
        Post post = postService.findPostById(id);
        String userEmail = principal.getName();
        UserInfo user = userService.loadUserByUsername(userEmail);
        post.setUserInfo(user);

        Homework home=post.isDoHome(userEmail);
        model.addAttribute("post", post);
        model.addAttribute("home", home);
        model.addAttribute("name" ,userEmail);
        return "/form";
    }

    /*
     * 게시글 생성
     */
    @PostMapping("/form")
    public  String postPost(@ModelAttribute PostDto postDto, Principal principal) throws IOException {
        String userEmail = principal.getName();
        UserInfo user = userService.loadUserByUsername(userEmail);
        postDto.setCreatedDate(LocalDateTime.now());
        postDto.setUpdatedDate(LocalDateTime.now());
        postDto.setUserInfo(user);
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

}
