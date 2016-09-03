package masterSpringMVC.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 登录的权限，像URI的地址
 * Created by OwenWilliam on 2016/5/21.
 */


@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .formLogin()
               .loginPage("/login")
               .defaultSuccessUrl("/profile")
               .and()
               .logout().logoutSuccessUrl("/login")
               .and()
               .authorizeRequests()
               .antMatchers("/webjars/**", "/login", "/signin/**", "/signup").permitAll()
               .anyRequest().authenticated();
    }
}
