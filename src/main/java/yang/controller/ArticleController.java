package yang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yang.model.Article;
import yang.service.ArticleService;
import yang.util.Reply;
import yang.util.ReplyArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-10 14:30
 **/
@RestController
public class ArticleController {

    public  static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Resource
    private ArticleService articleService;

    @RequestMapping(value = "/getArticle", method = RequestMethod.GET)
    @Transactional
    public ReplyArray getArticle(@RequestParam("offset") Integer offset,
                       @RequestParam("limit") Integer limit,
                       HttpServletRequest request, HttpServletResponse response) {
        ReplyArray reply = ReplyArray.buildTrue();
        logger.error("SessionId:" + request.getSession().getId());
        List<Article> articles=articleService.getArticles(offset,limit);
        for(Article article:articles)
        {
            reply.add(article);
        }
        return reply;
    }

    @RequestMapping(value = "/getArticleDetail", method = RequestMethod.GET)
    @Transactional
    public Reply getArticleDetail(@RequestParam("id") Integer id,
                                 HttpServletRequest request, HttpServletResponse response) {
        Reply reply = Reply.buildTrue();
        logger.error("SessionId:" + request.getSession().getId());
        Article article=articleService.getArticleDetail(id);
        if (article != null) {
            reply.put("article",article);
        }else {
            reply.setFalse();
        }
        return reply;
    }
}
