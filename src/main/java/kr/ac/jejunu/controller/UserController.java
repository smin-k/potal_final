package kr.ac.jejunu.controller;

import kr.ac.jejunu.dto.UserInfoDto;
import kr.ac.jejunu.service.HomeworkService;
import kr.ac.jejunu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/")
public class UserController {
    private final UserService userService;
    private final HomeworkService homeworkService;
    @PostMapping("/user")
    public String signup(UserInfoDto infoDto) { // 회원 추가
        userService.save(infoDto);
        return "redirect:/login";
    }

    @RequestMapping("/main")
    public String list(@PageableDefault Pageable pageable, Model model, Principal principal) {
        String userEmail = principal.getName();
        UserInfo userInfo = userService.loadUserByUsername(userEmail);
        model.addAttribute("homeList", homeworkService.findHomeworkByUserInfo(userInfo,pageable));
        return "/main";
    }


}
