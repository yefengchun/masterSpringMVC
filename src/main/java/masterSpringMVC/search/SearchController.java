package masterSpringMVC.search;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 查找时候的逻辑处理
 * Created by OwenWilliam on 2016/5/19.
 */

@Controller
public class SearchController {

    private TwitterSearch twitterSearch;

    @Autowired
    public SearchController(TwitterSearch twitterSearch) {
        this.twitterSearch = twitterSearch;
    }

    /**
     * 查找逻辑
     * @param searchType Twitter API ，如：mixed、recent、popular
     * @param keywords Twitter上的类型，如scala、java
     * @return
     */
    @RequestMapping("/search/{searchType}")
    public ModelAndView search(@PathVariable String searchType, @MatrixVariable List<String> keywords)
    {
        List<LightTweet> tweets = twitterSearch.search(searchType, keywords);
        ModelAndView modelAndView = new ModelAndView("resultPage");
        modelAndView.addObject("tweets", tweets);
        modelAndView.addObject("search", String.join(",", keywords));
        return modelAndView;
    }
}
