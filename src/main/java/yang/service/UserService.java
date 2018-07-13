package yang.service;

import yang.model.User;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 16:50
 **/
public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    void registerUser(User user);

    /**
     * 根据账号查找用户信息
     * @param username
     * @return
     */
    User selectByUsername(String username);

    User verifyUser(String username,String password);
}
