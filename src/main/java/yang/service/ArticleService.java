package yang.service;

import yang.model.Article;

import java.util.List;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-10 15:57
 **/
public interface ArticleService {
    List<Article> getArticles(int offset, int limit);

    Article getArticleDetail(int id);
}
