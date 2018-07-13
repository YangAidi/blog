package yang.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import yang.model.User;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 17:57
 **/
public class SessionUtil {

    private static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static void addAttribute(User user) {
        getSession().setAttribute("user", user);
    }

    public static User getUser() {
        return (User) SessionUtil.getSession().getAttribute("user");
    }

    public static Integer getId() {
        return getUser().getId();
    }

}
