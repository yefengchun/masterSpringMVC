package masterSpringMVC6.date;


import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.time.LocalDate;

/**
 * 用户的生日时间处理，输入日期必须是过去的某个时间
 * Created by OwenWilliam on 2016/5/15.
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastLocalDate.PastValidator.class)
@Documented
public @interface PastLocalDate {
    String message() default "{javax.validation.constraints.Past.message}";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};

    class PastValidator implements ConstraintValidator<PastLocalDate,
                LocalDate> {
        public void initialize(PastLocalDate past) {
        }

        //判断日期是否是空或是否是过去的
        public boolean isValid(LocalDate localDate,
                               ConstraintValidatorContext context) {
            return localDate == null || localDate.isBefore(LocalDate.now());
        }
    }


}
