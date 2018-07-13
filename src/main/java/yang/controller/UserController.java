package yang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import yang.enums.BackendEnum;
import yang.exception.BackendException;
import yang.model.User;
import yang.service.UserService;
import yang.util.Reply;
import yang.util.SessionUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 17:50
 **/

@RestController
@RequestMapping("/")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @Transactional
    public Reply login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response) {
        Reply reply = Reply.buildTrue();
        System.out.println("SessionId:" + request.getSession().getId());

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            reply.setErrMsg(BackendEnum.PARAM_ERROR);
            return reply;
        }
        try {
            User user = userService.verifyUser(username, password);
            if (user == null) {
                reply.setErrMsg(BackendEnum.PASSWORD_IS_INCORRECT_OR_USER_NOT_EXIST);
                return reply;
            }

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);


            //如果验证通过,则将用户信息传到前台
            request.setAttribute("user", user);
            request.getSession().setAttribute("user", user);

            // session里增加用户信息
            SessionUtil.addAttribute(user);
            reply.put("user", user);
            reply.put("sessionId",request.getSession().getId());
        } catch (BackendException e) {
            reply.setErrMsg(e);
        } catch (AuthenticationException e) {
            reply.setFalse();
        }
        return reply;
    }

    @RequestMapping(value = "/isLogin", method = RequestMethod.GET)
    @Transactional
    public Reply isLogin(HttpServletRequest request) {
        Reply reply = Reply.buildTrue();
        System.out.println("SessionId:" + request.getSession().getId());
        User user=SessionUtil.getUser();
        if(user!=null){
            reply.put("user",user);
        }else {
            reply.setFalse();
        }
        return reply;
    }

    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    @Transactional
    public Reply isLogin2() {
        Reply reply = Reply.buildTrue();
        User user=SessionUtil.getUser();
        if(user!=null){
            reply.put("user",user);
        }else {
            reply.setFalse();
        }
        return reply;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Transactional
    public Reply login(@RequestBody User theUser,
                       HttpServletRequest request, HttpServletResponse response) {
        Reply reply = Reply.buildTrue();
        System.out.println("SessionId:" + request.getSession().getId());
        String username=theUser.getUsername();
        String password=theUser.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            reply.setErrMsg(BackendEnum.PARAM_ERROR);
            return reply;
        }
        try {
            User user = userService.verifyUser(username, password);
            if (user == null) {
                reply.setErrMsg(BackendEnum.PASSWORD_IS_INCORRECT_OR_USER_NOT_EXIST);
                return reply;
            }

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);


            //如果验证通过,则将用户信息传到前台
            request.setAttribute("user", user);
            request.getSession().setAttribute("user", user);

            // session里增加用户信息
            SessionUtil.addAttribute(user);
            reply.put("user", user);
            reply.put("sessionId",request.getSession().getId());
        } catch (BackendException e) {
            reply.setErrMsg(e);
        } catch (AuthenticationException e) {
            reply.setFalse();
        }
        return reply;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Transactional
    public Reply register(@RequestBody User user) {
        Reply reply = Reply.buildTrue();
//        String username=user.get("username");
//        String password=user.get("password");
//        String nickname=user.get("nickname");
        String username=user.getUsername();
        String password=user.getPassword();
        String nickname=user.getNickname();
        try {
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)|| StringUtils.isEmpty(nickname)) {
                reply.setErrMsg(BackendEnum.PARAM_ERROR);
                return reply;
            }

            // 是否重复注册
            User newUser = userService.selectByUsername(username);

            // 已经被注册
            if (newUser != null) {
                reply.setErrMsg(BackendEnum.USER_REPEAT_REGISTER_EXCEPTION);
                return reply;
            }
            userService.registerUser(new User(username,password,nickname));
        } catch (BackendException e) {
            reply.setErrMsg(e);
        }

        return reply;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Reply logout(HttpServletRequest request) {
        Reply reply = Reply.buildTrue();
        System.out.println("SessionId:" + request.getSession().getId());
        try {
            //退出
            SecurityUtils.getSubject().logout();
            reply.put("judge", 0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return reply;
    }
}
