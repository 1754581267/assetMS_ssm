package bao.xy.service.Impl;

import bao.xy.dao.LoginMapper;
import bao.xy.model.Staff;
import bao.xy.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-15-21-26
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper mapper;

    public static String work = "";
    public static String id = "";
    public static String name = "";
    public static String pwd1 = "";

    @Override
    @Transactional( rollbackFor = Exception.class)
    public int login(String uname, String pwd, HttpServletRequest request) {
        List<Staff> list = mapper.login(uname);
        Staff staff = list.get(0);
        int code = 0;
        if (staff.getPowers().equals("已解锁") || staff.getPowers().equals("授权")) {

            // 判断用户名和密码是否正确
            if (staff.getUname().equals(uname)) {
                code += 1;
            }
            if (staff.getPwd().equals(pwd)) {
                code += 1;
                id = staff.getId();
                pwd1 = staff.getPwd();
                name = staff.getName();
                work = staff.getWork();
                HttpSession session = request.getSession();
                session.setAttribute("user", uname);
            }
        } else {
            code = -2;
        }
        return code;
    }

}
