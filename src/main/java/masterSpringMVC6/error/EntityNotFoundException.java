package masterSpringMVC6.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 找不到对应的实体的错误信息
 * Created by OwenWilliam on 2016/5/21.
 */
public class EntityNotFoundException extends Exception
{
    public EntityNotFoundException(String message)
    {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
