package masterSpringMVC6.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户普通登录
 * Created by OwenWilliam on 2016/5/21.
 */


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String authenticate()
    {
         return "login";
    }
}
