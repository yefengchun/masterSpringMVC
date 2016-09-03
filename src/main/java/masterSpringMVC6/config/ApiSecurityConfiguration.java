package masterSpringMVC6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 基础权限的设定，关于用户的权限
 * Created by OwenWilliam on 2016/5/21.
 */

@Configuration
@Order(1)//最先执行
public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter
{

    /**
     * 这个是数据库提取相匹配的。
     * 这里我们规定是固定的用户名和密码
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth)  throws Exception
    {
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and()
                .withUser("admin").password("admin").roles("USER", "ADMIN");
    }


    /**
     * 规定什么样的角色可以有什么样的操作
     * 如，User的只能查看，admin有CRUD
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.httpBasic()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/logout").permitAll()
                .antMatchers(HttpMethod.GET, "/api*//**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api*//**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api*//**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api*//**")
                .hasRole("ADMIN")
                .anyRequest().authenticated();*/
        http
                .antMatcher("/api/**")
                .httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET).hasRole("USER")
                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .anyRequest().authenticated();
    }
}
