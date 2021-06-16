//package kr.ac.jejunu.controller;
//
//import kr.ac.jejunu.repository.PostRepository;
//import kr.ac.jejunu.service.FileService;
//import kr.ac.jejunu.service.PostService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.*;
//import java.io.File;
//import java.time.LocalDateTime;
//
//
//@RestController
//@RequestMapping("/post")
//public class PostRestController {
//
//    /*
//     * 파일 업로드
//     */
//    @PostMapping("/form")
//    public ModelAndView postPost(@RequestParam("file") MultipartFile file, HttpServletRequest request )throws IOException {
//        String path = request.getServletContext().getRealPath("/")
//                + "/WEB-INF/static/" + file.getOriginalFilename();
//        File saveFile = new File(path);
//        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//        bufferedOutputStream.write(file.getBytes());
//        bufferedOutputStream.close();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("url", "/images/" + file.getOriginalFilename());
//        return modelAndView;
//    }
//
//}
