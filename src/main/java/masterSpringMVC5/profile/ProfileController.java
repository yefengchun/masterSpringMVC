package masterSpringMVC5.profile;

import masterSpringMVC5.date.USLocalDateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Profile后端业务的逻辑处理
 * Created by OwenWilliam on 2016/5/15.
 */

@Controller
public class ProfileController {

    @Autowired
    private UserProfileSession userProfileSession;

    @RequestMapping("/profile")
    public String displayProfile(ProfileForm profileForm)
    {
        return "profile/profilePage";
    }

    /**
     * 信息提交的处理
     * @param profileForm 实体
     * @param bindingResult  返回结果情况如何，如果有错，不希望使用，而是跳转错误页面
     * @return
     */
    @RequestMapping(value="/profile", params = {"save"}, method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult)
    {
       //提交信息的错误
        if (bindingResult.hasErrors())
        {
            return "profile/profilePage";
        }
        userProfileSession.saveForm(profileForm);
        return "redirect:/search/mixed;keywords=" + String.join(",", profileForm.getTastes());

    }

    /**
     * 添加Taste
     * @param profileForm
     * @return
     */
    @RequestMapping(value = "/profile", params = {"addTaste"})
    public String addRow(ProfileForm profileForm)
    {
        profileForm.getTastes().add(null);
        return "profile/profilePage";
    }

    /**
     * 删除已经添加的Taste
     * @param profileForm
     * @param req
     * @return
     */
    @RequestMapping(value = "/profile" , params = {"removeTaste"})
    public String removeRow(ProfileForm profileForm, HttpServletRequest req)
    {
        Integer rowId = Integer.valueOf(req.getParameter("removeTaste"));
        profileForm.getTastes().remove(rowId.intValue());
        return "profile/profilePage";
    }

    /**
     * 获取要提示用户输入的日期格式
     *  @ModelAttribute("dateFormat")就是映射到前端
     * @param locale
     * @return
     */
    @ModelAttribute("dateFormat")
    public String localeFormat(Locale locale)
    {
        return USLocalDateFormatter.getPattern(locale);
    }

    @Autowired
     public ProfileController(UserProfileSession userProfileSession)
     {
         this.userProfileSession = userProfileSession;
     }

    @ModelAttribute
    public ProfileForm getProfileForm()
    {
        return userProfileSession.toForm();
    }



}
