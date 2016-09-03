package masterSpringMVC7.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by OwenWilliam on 2016/5/19.
 */
@Service
public class SearchService {
    private Twitter twitter;


    @Autowired
    public SearchService(Twitter twitter)
    {
        this.twitter = twitter;
    }

    /**
     * 查找
     * @param searchType
     * @param keywords
     * @return
     */
    public List<LightTweet> search(String searchType, List<String> keywords)
    {
        List<SearchParameters> searches = keywords.stream().map(taste -> createSearchParam(searchType, taste)).collect(Collectors.toList());;

        List<LightTweet> results = searches.stream().map(params -> twitter.searchOperations().search(params))
                .flatMap(searchResults -> searchResults.getTweets().stream())
                .map(LightTweet :: ofTweet)
                .collect(Collectors.toList());
        return results;
    }
    /**
     * 去Twitter上查找分类的内容，如：mixed、recent、popular
     * @param searchType
     * @return
     */
    private SearchParameters.ResultType getResultType(String searchType)
    {

        for (SearchParameters.ResultType knowType : SearchParameters.ResultType.values())
        {
            //返回是要查找的对象
            if (knowType.name().equalsIgnoreCase(searchType))
            {
                return knowType;
            }
        }

        return SearchParameters.ResultType.RECENT;
    }

    /**
     * 配搭符合的分类，和对应分类 的内容
     * @param searchType 分类
     * @param taste 具体，如java/scala等
     * @return
     */
    private SearchParameters createSearchParam(String searchType, String taste)
    {
        //分类的
        SearchParameters.ResultType resultType = getResultType(searchType);
        //对应的具体的
        SearchParameters searchParameters = new SearchParameters(taste);
        searchParameters.resultType(resultType);
        searchParameters.count(3);
        return searchParameters;
    }



}