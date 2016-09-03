package masterSpringMVC6.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInAttempt;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * 通过Twitter来登录
 * Created by OwenWilliam on 2016/5/22.
 */
@Controller
public class SignupController
{
    private final ProviderSignInUtils signInUtils;

    @Autowired
    public SignupController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository connectionRepository)
    {
        signInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

    /**
     * 显示Twitter的登录页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/signup")
    public String singup(WebRequest request)
    {
        Connection<?> connection = signInUtils.getConnectionFromSession(request);

        //如果没有连接，那就连接吧
        if (connection != null)
        {
            AuthenticatingSignInAdapter.authenticate(connection);
            signInUtils.doPostSignUp(connection.getDisplayName(), request);
        }

        return "redirect:/profile";
    }
}
