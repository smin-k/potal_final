package kr.ac.jejunu.controller;

import java.time.LocalDateTime;

import kr.ac.jejunu.repository.PostRepository;
import kr.ac.jejunu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/post")
public class PostController {

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
    public String post(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
        model.addAttribute("post", postService.findPostByIdx(idx));
        return "/post/form";
    }

    /*
     * 게시글 생성
     */
    @PostMapping("/form")
    public  String postPost(@ModelAttribute Post post) {
        post.setCreatedDate(LocalDateTime.now());
        post.setUpdatedDate(LocalDateTime.now());
        postRepository.save(post);
        return "redirect:/post/list";
    }

    /*
     * 게시글 수정
     */
    @PutMapping("/{idx}")
    public ResponseEntity<?> putPost(@PathVariable("idx") Long idx, @RequestBody Post post) {
        Post updatePost = postRepository.getById(idx);
        updatePost.setTitle(post.getTitle());
        updatePost.setContent(post.getContent());
        updatePost.setUpdatedDate(LocalDateTime.now());
        postRepository.save(updatePost);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    /*
     * 게시글 삭제
     */
    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deletePost(@PathVariable("idx") Long idx) {
        postRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
