package masterSpringMVC7.user;

import masterSpringMVC7.error.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户的CRUD操作接口
 * Created by OwenWilliam on 2016/5/21.
 */

@Repository
public class UserRepository
{
    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    /**
     * 保存用户
     * @param user
     * @return
     */
    public User save(User user)
    {
        return userMap.put(user.getEmail(), user);
    }
    public User findOne(String email) throws EntityNotFoundException
    {
        if (!exists(email)) {
            //错误信息
            throw new EntityNotFoundException("User " + email + " cannot be found");
        }
        return userMap.get(email);
    }

    /**
     * 查找用户
     * @return
     */
    public List<User> findAll()
    {
        return new ArrayList<>(userMap.values());
    }

    /**
     * 删除用户
     * @param email
     * @throws EntityNotFoundException
     */
    public void delete(String email) throws EntityNotFoundException
    {
        if (!exists(email)) {
            throw new EntityNotFoundException("User " + email + " cannot be found");
        }
        userMap.remove(email);
    }

    /**
     * 是否存在email
     * @param email
     * @return
     */
    public boolean exists(String email)
    {
        return userMap.containsKey(email);
    }

    /**
     * 更新信息
     * @param email
     * @param user
     * @return
     * @throws EntityNotFoundException
     */
    public User update(String email, User user) throws EntityNotFoundException
    {
        if (!exists(email))
        {
            throw  new EntityNotFoundException("User " + email + " cannot be found");
        }
        user.setEmail(email);
        return userMap.put(email, user);
    }

    /**
     * 测试用
     * @param users
     */
    public void reset(User... users) {
        userMap.clear();
        for (User user : users) {
            save(user);
        }
    }
}
