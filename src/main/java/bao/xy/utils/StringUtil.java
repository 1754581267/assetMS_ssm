package bao.xy.utils;

/**
 * @Description: 字符串工具类
 * @CreateTime: 2020-08-15-19-24
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param str
     * @return false or true
     */
    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    /**
     * 判断字符串是否不为空
     * @param str
     * @return false or true
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否全都不为空
     * @param arr
     * @return false or true
     */
    public static boolean isAllNotEmpty(String...arr) {
        for (String str : arr) {
            if (isEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 删除最后一个符号
     *
     * @param data 字符串
     * @return 字符串子串
     */
    public static String delEnd(String data) {
        if (!"".equals(data)) {
            data = data.substring(0,data.length()-1);
        }
        return data;
    }
}
