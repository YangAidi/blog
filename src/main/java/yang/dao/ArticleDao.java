package yang.dao;

import org.apache.ibatis.annotations.*;
import yang.model.Article;

import java.util.List;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-10 11:18
 **/
@Mapper
public interface ArticleDao {
    @Select({"select * from article where id = #{0}"})
    Article getArticleDetail(int id);

    @Select({"select * from article order by id limit #{0},#{1}"})
    List<Article> selectArticles(int offset, int limit);

}

