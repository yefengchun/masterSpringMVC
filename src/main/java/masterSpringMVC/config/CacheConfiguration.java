package masterSpringMVC.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by OwenWilliam on 2016/5/23.
 */
@Configuration
@EnableCaching
public class CacheConfiguration
{
    @Bean
     public CacheManager cacheManager()
     {
         /*SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
         simpleCacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("searches")));
         return simpleCacheManager;*/

         GuavaCacheManager cacheManager = new
                 GuavaCacheManager("searches");
         cacheManager
                 .setCacheBuilder(
                         CacheBuilder.newBuilder()
                                 .softValues()
                                 .expireAfterWrite(10, TimeUnit.MINUTES)
                 );
         return cacheManager;
     }

}
