package cuit.pymjl.core.servlet.finance;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.finance.WageDetails;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.PageResult;
import cuit.pymjl.core.service.finance.FinanceService;
import cuit.pymjl.core.service.finance.impl.FinanceServiceImpl;
import cuit.pymjl.core.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Riou
 */
@WebServlet(name = "QueryAllWageSheetServlet", value = "/admin/getAllWageSheet")
public class QueryAllWageSheetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");
        if(StrUtil.isBlank(pageNum) || StrUtil.isBlank(pageSize)){
            throw new AppException("参数异常，参数不能为空");
        }
        FinanceService financeService = SingletonFactory.getInstance(FinanceServiceImpl.class);
        Page<WageDetails> wageSheet = financeService.getAllWageSheet(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        resp.getWriter().write(JsonUtils.toString(new PageResult<>(wageSheet)));
    }
}
