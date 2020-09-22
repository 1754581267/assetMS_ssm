package bao.xy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @CreateTime: 2020-09-04-09-10
 */
public class DateUtils {

    public static String Date() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dat = sdf.format(date);
        return dat;
    }


}
