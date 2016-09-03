package masterSpringMVC7.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import masterSpringMVC7.date.USLocalDateFormatter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.UrlPathHelper;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Locale;

/**
 * 定制SpringMVC的配置（如：日期、语言等）
 * 有点类似于Spring的xml的文件配置
 * 可以添加需要用的Bean,还有拦截器、事务控制等
 * Created by OwenWilliam on 2016/5/15.
 */
@Configuration
@EnableSwagger2
public class WebConfiguration extends WebMvcConfigurerAdapter {

    /**
     *格式转换处理
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry)
    {
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
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer()
    {
        //java 7
       /* EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer
                = new EmbeddedServletContainerCustomizer()
        {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container)
            {
                container.addErrorPages(new ErrorPage(MultipartException.class, "/uploadError"));
            }
        };*/

        //java 8
       /* EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer
                = container -> container.addErrorPages(new
                ErrorPage(MultipartException.class, "/uploadError"));
        return embeddedServletContainerCustomizer;*/
        return container -> container.addErrorPages(new
                ErrorPage(MultipartException.class, "/uploadError"));

    }

    /**
     * 默认SpringMVC不会放行拥有“，”的URL地址，所以我们需要设置false,可放行
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer)
    {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
        //允许访问地址有点的存在
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }

    @Bean
    public Docket userApi() {
        // the api documentation is available at http://localhost:8080/swagger-ui.html
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(path -> path.startsWith("/api/"))
                .build();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder)
    {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false);
        return objectMapper;

    }
}
