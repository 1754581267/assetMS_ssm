package bao.xy.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:
 * @CreateTime: 2020-08-16-15-47
 */
public class OutUtils {

    /**
     * 将json数据返回
     * @param response
     * @param json json数据
     * @throws IOException
     */
    public static void outJson(HttpServletResponse response, JSONObject json) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write(json.toJSONString());
        writer.flush();
    }

}
