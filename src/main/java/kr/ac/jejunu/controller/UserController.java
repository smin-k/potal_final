package kr.ac.jejunu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/")
public class UserController {


//    private final UserService userService;
//
//    @PostMapping("/user")
//    public String signup(UserInfoDto infoDto) { // 회원 추가
//        userService.save(infoDto);
//        return "redirect:/login";
//    }

//    private final UserDao userDao;
//
//    @PostMapping
//    public void save(@RequestBody UserInfo userInfo) {
//        userInfo.getComments().forEach(comment -> {
//            comment.setUser(userInfo);
//        });
//        userDao.save(userInfo);
//    }

//    @GetMapping("/{id}")
//    public User get(@PathVariable Integer id) {
//        User user = userDao.findById(id).orElse(new User());
//        System.out.println(user);
//        return user;
//    }

//    @GetMapping("/login")
//    public String login(){
//        return "user/login/login";
//    }

//    @GetMapping(value = "/login")
//    public String login(Model model) {
//        model.addAttribute("result", "login");
//        return "login";
//    }

//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if(authentication != null){
//            new SecurityContextLogoutHandler().logout(request,response,authentication);
//        }
//        return "redirect:/";
//    }
}
