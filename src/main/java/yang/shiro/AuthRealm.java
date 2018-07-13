package yang.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import yang.enums.BackendEnum;
import yang.exception.BackendException;
import yang.model.User;
import yang.service.UserService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 18:46
 **/
public class AuthRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 用于的权限的认证。
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString();

        SecurityUtils.getSubject().getSession().setAttribute(username, SecurityUtils.getSubject().getPrincipals());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 默认现在都有权限
        Set<String> permissions = new HashSet<>();
        permissions.add("public:*");
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 登录身份认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户账号
        if (token == null) {
            throw new BackendException(BackendEnum.PARAM_ERROR);
        }
        String username = token.getPrincipal().toString();
        User user = userService.selectByUsername(username);
        if (user == null) {
            throw new BackendException(BackendEnum.PASSWORD_IS_INCORRECT_OR_USER_NOT_EXIST);
        }
        //获取用户的密码
        String password = user.getPassword();
        //获取用户的盐
        String salt = user.getSalt();
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                user.getUsername(),
                //密码
                password,
                //盐
                new MySimpleByteSource(salt.getBytes()),
                //realm name
                getName()
        );
        return authenticationInfo;
    }
}
