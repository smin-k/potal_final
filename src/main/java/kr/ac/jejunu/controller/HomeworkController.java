package kr.ac.jejunu.controller;


import kr.ac.jejunu.dto.FileDto;
import kr.ac.jejunu.dto.HomeworkDto;
import kr.ac.jejunu.repository.HomeworkRepository;
import kr.ac.jejunu.service.FileService;
import kr.ac.jejunu.service.HomeworkService;
import kr.ac.jejunu.service.PostService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/post")
public class HomeworkController {

    @Autowired
    private FileService fileService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private HomeworkService homeworkService;


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

    @RequestMapping("/homework/{pid}/{hid}")
    public String post(@PathVariable("pid") Long pid,@PathVariable("hid") Long hid, Model model) {

        Post post = postService.findPostById(pid);
        Homework homework = homeworkService.findHomeworkById(hid);

        model.addAttribute("homework", homework );
        model.addAttribute("post", post);
        return "/homework";
    }

    @PostMapping("/homework")
    public String postHomework(Principal principal, @RequestParam("post_id") Long postId , @RequestParam("upload_file") MultipartFile file, @ModelAttribute HomeworkDto homeworkDto) throws IOException {
        Post post = postService.findPostById(postId);

        String userEmail = principal.getName();
        UserInfo user = userService.loadUserByUsername(userEmail);

        String filename = file.getOriginalFilename();
        String savePath = System.getProperty("user.dir")+"/build/resources/main/static";
        /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */

        String filePath = savePath + "\\" + filename;
        file.transferTo(new File(filePath));

        FileDto fileDto = new FileDto();
        fileDto.setFilename(filename);
        fileDto.setFilePath(filePath);

        homeworkDto.setFile(fileDto.toEntity());
        homeworkDto.setCreatedDate(LocalDateTime.now());
        homeworkDto.setPost(post);
        homeworkDto.setUserInfo(user);
        homeworkService.save(homeworkDto);

        return "redirect:/post/list";
    }


}
