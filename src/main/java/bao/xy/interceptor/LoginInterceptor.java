package bao.xy.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 自定义拦截器
 * @CreateTime: 2020-09-14-20-48
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return true 放过 false 拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        Object user = request.getSession().getAttribute("user");
//        System.out.println(uri + "->" +user);

        if (user != null) {
            return true;

        } else {
            response.sendRedirect("index.html");
            return false;
        }
    }

}
