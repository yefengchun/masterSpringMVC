package masterSpringMVC7.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by OwenWilliam on 2016/5/22.
 */

@Configuration
@Profile("redis")
@EnableRedisHttpSession
public class RedisConfig {
}
