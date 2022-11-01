package com.bluemsun.controller;

import com.bluemsun.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    @ResponseBody
    public Map login(@RequestBody User user, HttpServletRequest req) {
        Map map = new HashMap();
        if (user.getUsername().equals("tom") && user.getPassword().equals("1234")) {
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            map.put("code",0);
            map.put("msg","登陆成功");
        } else {
            map.put("code",1);
            map.put("msg","用户名或密码错误");
        }
        return map;
    }
    @GetMapping("/loginOut")
    @ResponseBody
    public Map loginOut(HttpServletRequest req) {
        Map map = new HashMap();
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null){
            session.removeAttribute("user");
        }
        map.put("code",0);
        map.put("msg","推出登陆成功");
        return map;
    }
}
