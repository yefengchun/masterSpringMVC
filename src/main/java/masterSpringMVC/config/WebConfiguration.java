package masterSpringMVC.config;

import masterSpringMVC.date.USLocalDateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.time.LocalDate;
import java.util.Locale;

/**
 * 定制SpringMVC的配置（如：日期、语言等）
 * 有点类似于Spring的xml的文件配置
 * 可以添加需要用的Bean,还有拦截器、事务控制等
 * Created by OwenWilliam on 2016/5/15.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    /**
     *格式转换处理
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(LocalDate.class, new USLocalDateFormatter());
    }

    /**
     * 从HTTP的请求地址中找到所属于的国家
     * @return
     *
     * ps:
     *  • FixedLocaleResolver: This fixes the locale defined in configuration. It
     *    cannot be changed once fixed.
     *  • CookieLocaleResolver: This allows the locale to be retrieved and saved in a
     *    cookie.
     *  • AcceptHeaderLocaleResolver: This uses the HTTP header sent by the
     *    user's browser to find the locale.
     *  • SessionLocaleResolver: This finds and stores the locale in an HTTP
     *    session.
     */
    @Bean
    public LocaleResolver localeResolver()

    {
        return new SessionLocaleResolver();
    }

    /**
     * 拦截器：拦截请求的地址
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor()
    {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        //在拦截的地址后面添加？lang=xx。如： http://localhost:8080/profile?lang=fr
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}