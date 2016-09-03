package masterSpringMVC7.search;

import java.util.List;

/**
 * Created by OwenWilliam on 2016/5/22.
 */
public interface TwitterSearch {
    List<LightTweet> search(String searchType, List<String> keywords);
}
