package bao.xy.controller;

import bao.xy.model.*;
import bao.xy.service.*;
import bao.xy.service.Impl.LoginServiceImpl;
import bao.xy.utils.SpringUtils;
import bao.xy.utils.StringUtil;
import bao.xy.utils.TableData;
import bao.xy.utils.WebUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-12-11-24
 */
@Controller
public class AccountController {

    @Resource
    private LoginService loginService;

    @Resource
    private StaffService staffService;

    @Resource
    private AssetService assetService;

    @Resource
    private JdbcService jdbcService;


    // 登录业务
    @RequestMapping("/login.ajax")
    @ResponseBody
    public String login(String uname, String password, HttpServletRequest request) {
        // 1.接收参数
        System.out.println(uname + "->" + password);
        // 状态码
        int code = 0;
        // 2. 校验参数
        if (!StringUtil.isAllNotEmpty(uname, password)){
            // 空参数操作
            code = -1;
        } else {
            // 3.调用业务处理(service)
            code = loginService.login(uname, password, request);
        }
        // 4.处理结果
        JSONObject json = new JSONObject();
        json.put("code", code);
        return json.toJSONString();
    }

    // 退出登录
    @RequestMapping("/out")
    @ResponseBody
    public void outLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 将session值 清空
            request.getSession().setAttribute("user", null);
            // 退出提示
            WebUtil.jump(response, "index.html", "退出成功，三秒后跳转，如未跳转请点击这里", 3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/change")
    @ResponseBody
    public String change(String str, String password, String newPwd, HttpServletRequest request) {
        String code = "";

        // 调用业务 修改密码
        if ("change".equals(str)) {
            boolean dochange = jdbcService.updt(1, "staff", "password", newPwd, "id", LoginServiceImpl.id);
            if (dochange) {
                request.getSession().setAttribute("user", null);
                code = "change";

            } else {
                code = "notchange";
            }
        }

        // 验证原密码是否正确
        if ("pwd".equals(str)) {
            if (LoginServiceImpl.pwd1.equals(password)) {
                code = "same";
            } else {
                code = "not";
            }
        }

        String wrok = "";
        String name = "";

        if ("work".equals(str)) {
            wrok = LoginServiceImpl.work;
            name = LoginServiceImpl.name;
        }

        JSONObject json = new JSONObject();
        json.put("work", wrok);
        json.put("name", name);
        json.put("code", code);
        return json.toJSONString();
    }

    @RequestMapping("/staff.ajax")
    @ResponseBody
    public String staff(HttpServletRequest request, String str, Integer index, Staff staff) {

        //一页有多少条数据
        TableData<Staff> td = new TableData<>();

        String code = "";

        if ("paging".equals(str)) {
            td = staffService.paging(index, staff.getWork(), staff.getName(), staff.getUname());
        }

        // 调用的删除业务
        if ("del".equals(str)) {
            List<Integer> idList = jdbcService.idList(staff.getId());
            code = jdbcService.delIds("Staff", idList);
        }

        if ("exist".equals(str)) {
            code = jdbcService.isOrNot("管理员", "adm");
        }

        if ("empower".equals(str)) {
            code = staffService.updp(staff.getId(), "已解锁");
        }

        if ("revoke".equals(str)) {
            code = staffService.updp(staff.getId(), "已锁定");
            if (code.equals("myself")) {
                request.getSession().setAttribute("user", null);
            }
        }

        if ("add".equals(str)) {
            // 调用新增业务
            code = staffService.add(staff);

        }

        if ("upd".equals(str)) {
            code = staffService.updt(staff);
        }

        td.setCode(code);
        return WebUtil.returnJsonTd(td);
    }


    // 资产
//    @RequestMapping("/assets.ajax")
//    @ResponseBody
//    public String asset(HttpServletRequest request, Integer index, String str, String ffid, String fpid, Asset asset) {
//
//        TableData<Asset> td = new TableData<>();
//        // 状态码
//        String code = "";
//
//        // 员工业务对象
//        if ("paging".equals(str)) {
//            td = assetService.paging(index, asset.getAssetClass(), asset.getState());
//        }
//        //分页
//        List<Asset> list = new AssetsDaoImpl().doShow();
//        List<Asset> data = new ArrayList<>();
//        // 页数
//        String indexStr = request.getParameter("index");
//        //一页有多少条数据
//        int size = 8;
//        // 最后一页的数据数量
//        int maxSize = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
//        if ("paging".equals(str)) {
//            data = assetService.paging(indexStr, size, list, data);
//        }
//
//        // 删除
//        if ("del".equals(str)) {
//            code = JdbcUtils.delete("assets", "id", id);
//        }
//
//        // 调用新增业务
//        if ("add".equals(str)) {
//            code = new AssetsDaoImpl().add(aclass, fid, pid, LoginDaoImpl.id, state);
//        }
//
//        // 验证是否为保管员
//        if ("exist".equals(str)) {
//            code = JdbcUtils.isOrNot("保管员", "kep");
//        }
//
//        // 验证财务id
//        if ("ffid".equals(str)) {
//            code = assetService.ffid(ffid);
//        }
//
//        // 验证资源id
//        if ("fpid".equals(str)) {
//            code = assetService.fpid(fpid);
//        }
//
//        // 更新
//        if ("updt".equals(str)) {
//            code = new AssetsDaoImpl().updt(upid, upaclass, upfid, uppid, LoginDaoImpl.id, upstate);
//        }

//        JSONObject json = new JSONObject();
//        json.put("list", data);
//        json.put("code", code);
//        json.put("maxSize", maxSize);
//        return json.toJSONString();
//        return null;
//    }
}


