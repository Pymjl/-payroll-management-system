package cuit.pymjl.core.servlet.finance;

import cn.hutool.core.util.StrUtil;
import cuit.pymjl.core.entity.finance.WageDetails;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.R;
import cuit.pymjl.core.service.finance.FinanceService;
import cuit.pymjl.core.service.finance.impl.FinanceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Riou
 */
@WebServlet(name = "QueryWageSheetById", value = "/admin/getWageSheetById")
public class QueryWageSheetById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (StrUtil.isBlank(id)){
            throw new AppException("参数异常，参数不能为空");
        }
        FinanceService financeService = SingletonFactory.getInstance(FinanceServiceImpl.class);
        WageDetails wageDetails = financeService.getWageSheetById(Long.parseLong(id));
        resp.getWriter().println(R.success(wageDetails));
    }
}
