package cuit.pymjl.core.servlet.finance;

import cn.hutool.core.util.ObjectUtil;
import cuit.pymjl.core.entity.finance.BasicWage;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.R;
import cuit.pymjl.core.service.finance.FinanceService;
import cuit.pymjl.core.service.finance.impl.FinanceServiceImpl;
import cuit.pymjl.core.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author 622
 */
@Slf4j
@WebServlet(name = "UpdateBasicWage", value = "/update/basicWage")
public class UpdateBasicWage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        log.info("开始从Body中读取Json字符串......");
        BufferedReader reader = request.getReader();
        String json = "";
        String line;
        while ((line = reader.readLine()) != null) {
            json = json.concat(line);
        }
        BasicWage basicWage = JsonUtils.toBean(json, BasicWage.class);
        if (ObjectUtil.isNull(basicWage) ||
                (basicWage.getCommonWage() == null && basicWage.getManagerWage() == null) ){
            throw new AppException("所有参数全为空");
        }
        log.info("读取数据成功==>[{}]", basicWage);
        FinanceService financeService = SingletonFactory.getInstance(FinanceServiceImpl.class);
        financeService.updateBasicWage(basicWage);
        response.getWriter().println(R.success());
    }
}
