package com.huike.controller;

import com.huike.entity.Employee;
import com.huike.service.UserService;
import com.huike.util.ReturnObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    /*
    *
    *   a.请求地址 : user/login
        b.请求参数: username、password    (Java是面向对象的语言-----万事万物皆对象)
        c.功能：根据用户输入的用户名密码去数据库中匹配
        d.返回：success、msg
    * */
    @RequestMapping("/login")
    @ResponseBody
    public ReturnObject fanfan(Employee employee){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(employee.getUsername(),employee.getPassword());
        try{
            subject.login(token);
            return new ReturnObject();
        }catch (Exception e){
            return new ReturnObject().mark("亲，用户名或密码不正确");
        }
    }
}
