package masterSpringMVC.user.api;


import masterSpringMVC.error.EntityNotFoundException;
import masterSpringMVC.user.User;
import masterSpringMVC.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息的API操作
 * Created by OwenWilliam on 2016/5/21.
 */

@RestController
@RequestMapping("/api")//这个是说明说明访问都是以/api/**来访问下面的方法，如：/api/user
//如果添加 @Secured("ROLE_ADMIN")，说明只有这个角色可以访问
public class UserApiController
{
    private UserRepository userRepository;

    @Autowired
    public UserApiController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    /**
     * 查找用户 Get
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    /**
     * 创建用户 POST
     * @param user
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        HttpStatus status = HttpStatus.OK;

        if (!userRepository.exists(user.getEmail()))
        {
            status = HttpStatus.CREATED;
        }
        User saved = userRepository.save(user);
        return new ResponseEntity<>(saved, status);
    }

    /**
     * 更新用户 PUT
     * @param email
     * @param user
     * @return
     * @throws EntityNotFoundException
     */
    @RequestMapping(value = "/user/{email}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable String email,@RequestBody User user) throws EntityNotFoundException
    {
        User saved = userRepository.update(email, user);
        return new ResponseEntity<User>(saved, HttpStatus.CREATED);
    }

    /**
     * 删除用户 DELETE
     * @param email
     * @return
     * @throws EntityNotFoundException
     */
    @RequestMapping(value = "/user/{email}", method = RequestMethod.DELETE)
    public  ResponseEntity<User> deleteUser(@PathVariable String email) throws EntityNotFoundException
    {
        userRepository.delete(email);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

}