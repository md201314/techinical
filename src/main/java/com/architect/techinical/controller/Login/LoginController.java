package com.architect.techinical.controller.Login;

import com.architect.techinical.controller.Login.model.UserLogin;
import com.architect.techinical.controller.main.model.AttendanceDatetime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/index")
    public String loginForm(Model model) {
        model.addAttribute("userLogin", new UserLogin());
        return "index";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute UserLogin userLogin, Model model){
        //model.addAttribute("userLogin", new UserLogin());
        if("admin".equals(userLogin.getUsername())&&"123456".equals(userLogin.getPasswd())) {
            //设置账号为：admin，密码为：123456
            //redirect是重定向
            return "redirect:/main/getAttendance";
        }
        else{
            //账号或者密码错误，返回主页面
            return "index";
        }
    }
}
