package yang.service.impl;

import org.springframework.stereotype.Service;
import yang.dao.ArticleDao;
import yang.enums.BackendEnum;
import yang.exception.BackendException;
import yang.model.Article;
import yang.service.ArticleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-10 15:57
 **/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;
    @Override
    public List<Article> getArticles(int offset, int limit) {
        try {
            return articleDao.selectArticles(offset,limit);
        } catch (Exception e) {
            throw new BackendException(BackendEnum.QUERY_EXCEPTION);
        }
    }

    @Override
    public Article getArticleDetail(int id) {
        try {
            return articleDao.getArticleDetail(id);
        } catch (Exception e) {
            throw new BackendException(BackendEnum.QUERY_EXCEPTION);
        }
    }
}
