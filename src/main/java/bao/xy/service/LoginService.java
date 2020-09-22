package bao.xy.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @CreateTime: 2020-09-15-21-26
 */
public interface LoginService {

    int login(String uname, String pwd, HttpServletRequest request);

}
