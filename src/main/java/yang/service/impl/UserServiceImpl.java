package yang.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import yang.dao.UserDao;
import yang.enums.BackendEnum;
import yang.exception.BackendException;
import yang.model.User;
import yang.service.UserService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 16:50
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public void registerUser(User user) {
        try {
            String username = user.getUsername();
            String pwd = user.getPassword();
            Integer roleId = user.getRoleId();
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(pwd)) {
                throw new BackendException(BackendEnum.PARAM_ERROR);
            }
            SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
            // 生成盐
            String salt = secureRandomNumberGenerator.nextBytes().toHex();
            // n两次md5加密然后再hex，更改此加密算法需要更改myrleam认证算法
            String password = new SimpleHash("md5", pwd, ByteSource.Util.bytes(salt), 2).toHex();

            user.setPassword(password);
            // 目前默认是0
            user.setRoleId(0);
            user.setSalt(salt);
            user.setRegisterTime(new Date());
            user.setUpdateTime(new Date());
            userDao.addUser(user);
        } catch (Exception e) {
            throw new BackendException(BackendEnum.ADD_EXCEPTION);
        }
    }

    @Override
    public User selectByUsername(String username) {
        try {
            return userDao.getUserByUserName(username);
        } catch (Exception e) {
            throw new BackendException(BackendEnum.QUERY_EXCEPTION);
        }
    }

    @Override
    public User verifyUser(String username, String password) {
        User user = userDao.getUserByUserName(username);
        if(user == null) {
            return null;
        }
        String newPassword = new SimpleHash("md5", password, ByteSource.Util.bytes(user.getSalt()), 2).toHex();
        if(!newPassword.equals(user.getPassword())){
            return null;
        }
        return user;
    }
}
