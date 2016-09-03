package masterSpringMVC.date;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 根据本地的国家，判断用什么样的日期
 * Created by OwenWilliam on 2016/5/15.
 */
public class USLocalDateFormatter implements Formatter<LocalDate> {
    /**美国的日期格式*/
    public static final String US_PATTERN = "MM/dd/yyyy";
    /**其它国家的日期格式*/
    public static final String NORMAL_PATTERN = "dd/MM/yyyy";

    /**
     * 语法上的解析
     * @param text
     * @param locale
     * @return
     * @throws ParseException
     */
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(getPattern(locale)));
    }

    /**
     * 将locale转换为String
     * @param object
     * @param locale
     * @return
     */
    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ofPattern(getPattern(locale)).format(object);
    }

    /**
     * 判断本地是美国，还是其它国家
     * @param locale
     * @return
     */
    public static String getPattern(Locale locale)
    {
        return isUnitedStates(locale) ? US_PATTERN : NORMAL_PATTERN;
    }

    /**
     * 是否是美国
     * @param locale
     * @return
     */
    private  static boolean isUnitedStates(Locale locale)
    {
        return Locale.US.getCountry().equals(locale.getCountry());
    }
}
