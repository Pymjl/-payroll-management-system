package cuit.pymjl.core.servlet.finance;

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
@WebServlet(name = "SummarySheetServlet", value = "/admin/summary")
public class SummarySheetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FinanceService financeService = SingletonFactory.getInstance(FinanceServiceImpl.class);
        int result = financeService.summarySheet();
        if(result != 1){
            throw new AppException("汇总失败");
        } else {
            resp.getWriter().println(R.success());
        }
    }
}
