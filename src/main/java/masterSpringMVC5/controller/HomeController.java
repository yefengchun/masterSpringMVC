package masterSpringMVC5.controller;

import masterSpringMVC5.profile.UserProfileSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * It is responsible for redirecting
 *a user arriving at the root of our website either to their profile if it's incomplete or to
 *the resultPage if their tastes are available
 * Created by OwenWilliam on 2016/5/19.
 */
@Controller
public class HomeController
{
    private UserProfileSession userProfileSession;
    @Autowired
    public HomeController(UserProfileSession userProfileSession) {
        this.userProfileSession = userProfileSession;
    }
    @RequestMapping("/")
    public String home() {
        List<String> tastes = userProfileSession.getTastes();
        if (tastes.isEmpty()) {
            return "redirect:/profile";
        }
        return "redirect:/search/mixed;keywords=" + String.join(",", tastes);
    }
}
