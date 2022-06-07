package cuit.pymjl.core.servlet.finance;

import cuit.pymjl.core.entity.finance.BasicWage;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.R;
import cuit.pymjl.core.service.finance.FinanceService;
import cuit.pymjl.core.service.finance.impl.FinanceServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Riou
 */
@WebServlet(name = "QueryBasicWage", value = "/basicWage")
public class QueryBasicWage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException{

        FinanceService financeService = SingletonFactory.getInstance(FinanceServiceImpl.class);
        BasicWage basicWage = financeService.queryBasicWage();
        response.getWriter().println(R.success(basicWage));
    }
}
