package yang.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yang.exception.UnauthorizedException;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 23:05
 **/
@RestController
public class ErrorController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        try {
            testException();
        } catch (UnauthorizedException e) {
            logger.error("MsgDes\t" + e.getMsgDes());
            logger.error("RetCd\t" + e.getRetCd());
            return e.toString();
        }
        return "";
    }

    public void testException() throws UnauthorizedException {
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("", "");
            Subject subject = SecurityUtils.getSubject();
            //完成登录
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            throw new UnauthorizedException("401", "NoSession");
        }
    }
}
