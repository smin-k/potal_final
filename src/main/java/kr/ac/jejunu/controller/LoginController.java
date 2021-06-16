package kr.ac.jejunu.controller;

import kr.ac.jejunu.dto.UserInfoDto;
import kr.ac.jejunu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/")
public class LoginController {

    private final UserService userService;

//    @GetMapping(value = "/login")
//    public String login(Model model) {
//          model.addAttribute("result", "login");
//          return "login";
//    }
//
//    @GetMapping(value = "/signup")
//    public String signup(Model model) {
//        model.addAttribute("result", "login");
//        return "signup";
//    }

    @PostMapping("/user")
    public String signup(UserInfoDto infoDto) { // 회원 추가
        userService.save(infoDto);
        return "redirect:/login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }


//    private final UserDao userDao;
//
//    @GetMapping(value = "/login")
//    public String login(Model model) {
//        model.addAttribute("result", "login");
//        return "login";
//    }

}
