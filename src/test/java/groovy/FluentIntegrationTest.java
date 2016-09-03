package groovy;

import masterSpringMVC7.MasterSpringMvc7Application;
import masterSpringMVC7.search.StubTwitterSearchConfig;
import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by OwenWilliam on 2016/5/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        MasterSpringMvc7Application.class,
        StubTwitterSearchConfig.class
})
@WebIntegrationTest(randomPort = true)
public class FluentIntegrationTest extends FluentTest{
    @Value("${local.server.port}")
    private int serverPort;
    @Override
    public WebDriver getDefaultDriver() {

        return new PhantomJSDriver();
       // return new FirefoxDriver();
    }
    public String getDefaultBaseUrl() {
        return "http://localhost:" + serverPort;
    }
    @Test
    public void hasPageTitle() {
        goTo("/");
        assertThat(findFirst("h2").getText()).isEqualTo("Login");
    }
}
