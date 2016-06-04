package masterSpringMVC.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by OwenWilliam on 2016/5/13.
 */
@Controller
public class TweetController {

    @Autowired
    private Twitter twitter;

    /**
     * 跳转到查找页面
     * @return searchPage.html
     */
    @RequestMapping("/")
    public String home()
    {
        return "searchPage";
    }

    /**
     * 处理searchPage页面传过来的值，做逻辑处理
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/postSearch")
    public String postSearch(HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        String search = request.getParameter("search");
        //如果是struts的字段，那么是错误，提示错误信息
        if (search.toLowerCase().contains("struts")) {
            //将错误信息放入
            redirectAttributes.addFlashAttribute("error", "Try using spring instead!");
            //跳转search的页面
            return "redirect:/";
        }
        redirectAttributes.addAttribute("search", search);
        //跳转到result的处理逻辑
        return "redirect:result";
    }

    /**
     * 请求Twitter的网站，查找需要的信息
     * @param search
     * @param model
     * @return
     */
    @RequestMapping("/result")
    public String hello(@RequestParam(defaultValue = "owenWilliam") String search, Model model)
    {
        //请求后返回的结果
       SearchResults searchResults = twitter.searchOperations().search(search);
        List<Tweet> tweets = searchResults.getTweets();
        model.addAttribute("tweets", tweets);
        model.addAttribute("search", search);
        return "resultPage";
    }
}
