package masterSpringMVC5.api;

import masterSpringMVC5.search.LightTweet;
import masterSpringMVC5.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 请求API，请求结果以json的形式返回给客户端
 * Created by OwenWilliam on 2016/5/21.
 */

@RestController
@RequestMapping("/api/search")
public class SearchApiController
{
    private SearchService searchService;

    @Autowired
    public SearchApiController(SearchService searchService)
    {
        this.searchService = searchService;
    }

    /**
     *  It tells Spring to serialize the return type to the appropriate format, which is JSON by default.
     * @param searchType
     * @param keywords
     * @return
     */
    @RequestMapping(value = "/{searchType}", method = RequestMethod.GET)
    public List<LightTweet> search(@PathVariable String searchType, @MatrixVariable List<String> keywords)
    {
        return searchService.search(searchType, keywords);
    }
}
