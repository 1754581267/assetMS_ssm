package bao.xy.controller;

import bao.xy.model.*;
import bao.xy.service.*;
import bao.xy.service.Impl.LoginServiceImpl;
import bao.xy.utils.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private JdbcService jdbcService;


    // 登录业务
    @RequestMapping("/login.ajax")
    @ResponseBody
    public String login(String uname, String password, HttpServletRequest request) {
        // 1.接收参数
//        System.out.println(uname + "->" + password);
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
            WebUtil.jump(response, "index.html", "退出成功，三秒后跳转，如未跳转请点击这里", 2);
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

    @Resource
    private StaffService staffService;

    @RequestMapping("/staff.ajax")
    @ResponseBody
    public String staff(HttpServletRequest request, String str, PageDate pd, Staff staff) {

        //一页有多少条数据
        TableData<Staff> td = new TableData<>(pd);

        String code = "";

        if ("paging".equals(str)) {
            td = staffService.paging(pd);
            code = jdbcService.isOrNot("管理员", "adm");
        }

        List<Integer> idList = new ArrayList<>();
        if (StringUtil.isNotEmpty(staff.getId())) {
            idList = jdbcService.idList(staff.getId());
        }

        // 调用的删除业务
        if ("del".equals(str)) {
            code = jdbcService.delIds("staff", idList);
        }

        if ("empower".equals(str)) {
            code = staffService.updp(idList, "已解锁");
        }

        if ("revoke".equals(str)) {
            code = staffService.updp(idList, "已锁定");
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

    @Resource
    private AssetService assetService;

    // 资产
    @RequestMapping("/assets.ajax")
    @ResponseBody
    public String asset(PageDate pd, String str, String ffid, String fpid, Asset asset) {
        TableData<Asset> td = new TableData<>(pd);
        // 状态码
        String code = "";

        // 员工业务对象
        if ("paging".equals(str)) {
            td = assetService.paging(pd);
            code = jdbcService.isOrNot("保管员", "kep");
        }

        // 删除
        if ("del".equals(str)) {
            List<Integer> idList = jdbcService.idList(asset.getId());
            code = jdbcService.delIds("assets", idList);
        }

        // 调用新增业务
        if ("add".equals(str)) {
            asset.setCareStaffId(LoginServiceImpl.id);
            code = assetService.add(asset);
        }

        // 验证财务id
        if ("ffid".equals(str)) {
            code = assetService.ffid(ffid, asset.getId());
        }

        // 验证资源id
        if ("fpid".equals(str)) {
            code = assetService.fpid(fpid, asset.getId());
        }

        // 更新
        if ("updt".equals(str)) {
            asset.setCareStaffId(LoginServiceImpl.id);
            code = assetService.updt(asset);
        }

        td.setCode(code);
        return WebUtil.returnJsonTd(td);
    }

    @Resource
    private FinanceService financeService;

    @RequestMapping("/finance.ajax")
    @ResponseBody
    public String finance (PageDate pd, String str, Finance finance) {
        TableData<Finance> td = new TableData<>(pd);

        String code = "";

        if ("paging".equals(str)) {
            td = financeService.paging(pd);
            code = jdbcService.isOrNot("保管员", "kep");
        }

        // 删除
        if ("del".equals(str)) {
            List<Integer> idList = jdbcService.idList(finance.getId());
            code = jdbcService.delIds("finance", idList);
        }


        // 生成凭证号
        if ("form".equals(str)) {
            Date date = new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
            code = sdf.format(date);
        }

        // 新增
        if ("add".equals(str)) {
            finance.setStaffId(LoginServiceImpl.id);
            finance.setEntryDate(DateUtils.Date());
            code = financeService.add(finance);
        }

        // 修改
        if ("updt".equals(str)) {
            code = financeService.updt(finance);
        }

        td.setCode(code);
        return WebUtil.returnJsonTd(td);
    }

    @Resource
    private ProductService productService;

    // 产品
    @RequestMapping("/product.ajax")
    @ResponseBody
    public String product(String str, PageDate pd, Product product) {
        TableData<Product> td = new TableData<>(pd);
        String code = "";

        if ("paging".equals(str)) {
            td = productService.paging(pd);
            code = jdbcService.isOrNot("保管员", "kep");
        }

        if ("del".equals(str)) {
            List<Integer> idList = jdbcService.idList(product.getId());
            code = jdbcService.delIds("product", idList);
        }

        if ("form".equals(str)) {
            Date date = new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
            code = sdf.format(date);
        }

        if ("add".equals(str)) {
            product.setSrorageTime(DateUtils.Date());
            code = productService.add(product);
        }

        if ("updt".equals(str)) {
            code = productService.updt(product);
        }

        td.setCode(code);
        return WebUtil.returnJsonTd(td);
    }

    @Resource
    private DetailsService detailsService;

    // 资产详情
    @RequestMapping("/details.ajax")
    @ResponseBody
    public String details(String str, PageDate pd, Details details) {
        TableData<Details> td = new TableData<>(pd);
        String code = null;

        String acode = null;
        String fcode = null;
        String pcode = null;

        if ("paging".equals(str)) {
            td = detailsService.paging(pd);
            code = jdbcService.isOrNot("保管员", "kep");
        }

        if ("add".equals(str)) {

            Product product = new Product(null, details.getProductName(), details.getSpecification(), details.getUnit(), details.getNumber(), details.getUnitPrice(), DateUtils.Date());
            product.setSrorageTime(DateUtils.Date());
            pcode = productService.add(product);


            String state = "可用";
            if ("报废".equals(details.getAssetsState())) {
                state = "销账";
            }
            Finance finance = new Finance(null, LoginServiceImpl.id, details.getProof(), DateUtils.Date(), state);
            finance.setStaffId(LoginServiceImpl.id);
            finance.setEntryDate(DateUtils.Date());
            fcode = financeService.add(finance);

            Asset asset = new Asset(null, details.getAssetClass(), finance.getId(), product.getId(), LoginServiceImpl.id, details.getAssetsState());
            acode = assetService.add(asset);
            JSONObject json = new JSONObject();
            json.put("acode", acode);
            json.put("fcode", fcode);
            json.put("pcode", pcode);
            return json.toJSONString();
        }

        if ("updt".equals(str)) {
            Product product = new Product(details.getProductId(), details.getProductName(), details.getSpecification(), details.getUnit(), details.getNumber(), details.getUnitPrice(), null);
            pcode = productService.updt(product);
            Asset asset = new Asset(details.getAssetsId(), details.getAssetClass(), details.getFinanceId(), details.getProductId(), LoginServiceImpl.id, details.getAssetsState());
            acode = assetService.updt(asset);
            String state = "可用";
            if ("报废".equals(details.getAssetsState())) {
                state = "销账";
            }
            Finance finance = new Finance(details.getFinanceId(), null, null, null, state);
            fcode = financeService.updt(finance);
            JSONObject json = new JSONObject();
            json.put("acode", acode);
            json.put("fcode", fcode);
            json.put("pcode", pcode);
            return json.toJSONString();
        }

        if ("del".equals(str)) {
            List<Integer> aidList = jdbcService.idList(details.getAssetsId());
            List<Integer> fidList = jdbcService.idList(details.getFinanceId());
            List<Integer> pidList = jdbcService.idList(details.getProductId());
            System.out.println(aidList+"-"+fidList+"-"+pidList);
            acode = jdbcService.delIds("assets", aidList);
            fcode = jdbcService.delIds("finance", fidList);
            pcode = jdbcService.delIds("product", pidList);
            JSONObject json = new JSONObject();
            json.put("acode", acode);
            json.put("fcode", fcode);
            json.put("pcode", pcode);
            return json.toJSONString();
        }

        td.setCode(code);
        return WebUtil.returnJsonTd(td);
    }

    @Resource
    private ApplicationService applicationService;
    // 申请业务
    @RequestMapping("/app.ajax")
    @ResponseBody
    public String application(String str, PageDate pd, Application application) {

        TableData<Application> td = new TableData<>(pd);
        String code = null;

        if ("paging".equals(str)) {
            td = applicationService.paging(pd);
            code = jdbcService.isOrNot("保管员", "kep");
        }

        // 删除业务
        if ("del".equals(str)) {
            List<Integer> idList = jdbcService.idList(application.getId());
            code = jdbcService.delIds("application", idList);
        }

        // 查询资产状态
        if ("finda".equals(str)) {
            code = applicationService.finda(application.getAssetsId());
        }

        // 新增业务
        if ("add".equals(str)) {
            code = applicationService.add(application);
        }

        // 归还业务
        if ("ret".equals(str)) {
            code = applicationService.ret(application);
        }

        // 报废业务
        if ("scrap".equals(str)) {
            code = applicationService.scrap(application);
        }

        td.setCode(code);
        return WebUtil.returnJsonTd(td);
    }
}


