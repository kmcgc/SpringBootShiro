package com.stark.controller.page;

import com.stark.entity.User;
import com.stark.shiro.ShiroUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 如果已经登录了，直接转跳index
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(Model model) {
        if (ShiroUtils.getSubject().isAuthenticated()) {
            return "redirect:index";
        } else {
            model.addAttribute("msg", "请登录");
            return "login";
        }
    }

    /**
     * 登录成功会直接按配置转跳
     * 登录失败的时候会进入到这个函数里，可以提取出登录报错
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(HttpServletRequest request, Model model) {
        Object exception = request.getAttribute("shiroLoginFailure");
        String msg = "登录失败";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "用户名不正确，请重新输入";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "密码错误，请重新输入";
            } else if (LockedAccountException.class.getName().equals(exception)) {
                msg = "账号被锁定，请联系管理员";
            } else {
                msg = "发生未知错误，请联系管理员。";
            }
        }
        model.addAttribute("msg", msg);
        return "login";
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String user(Model model) {
        User user = (User) ShiroUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admin(Model model) {
        User user = (User) ShiroUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "admin";
    }

    @RequestMapping(value = {"/403"}, method = RequestMethod.GET)
    public String noAuth() {
        return "403";
    }

    @RequestMapping(value = {"/adduser"}, method = RequestMethod.GET)
    public String addUser() {
        return "adduser";
    }

}