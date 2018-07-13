package yang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import yang.model.User;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 17:53
 **/
@Mapper
public interface UserDao {
    @Select("SELECT * FROM user WHERE username = #{0}")
    User getUserByUserName(String username);

    @Insert("INSERT INTO user (roleId, username, password,salt,registerTime,nickname) VALUES (#{roleId}, #{username},#{password},#{salt},#{registerTime},#{nickname}) ")
    int addUser(User user);
}
