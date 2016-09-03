package masterSpringMVC7.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * 连接到Twitter，登录
 * Created by OwenWilliam on 2016/5/22.
 */
@Component
public class AuthenticatingSignInAdapter implements SignInAdapter {

    public static void authenticate(Connection<?> connection)
    {

        //获取用户的提供信息
        UserProfile userProfile = connection.fetchUserProfile();
        String username = userProfile.getUsername();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("====:" +String.format("User %s %s connected.",
                userProfile.getFirstName(), userProfile.getLastName()));
       // System.out.println("==getFirstName:" + userProfile.getFirstName() + ",getLastName():" + userProfile.getLastName());
    }

    @Override
    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
       // System.out.println("=======");
        authenticate(connection);
        return null;
    }
}
