package cuit.pymjl.core.service.finance;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.finance.BasicWage;

/**
 * @author Riou
 */
public interface FinanceService {
    /**
     * 获取基本工资
     * @return BasicWage
     */
    BasicWage queryBasicWage();

    /**
     * 修改基本工资
     * @param basicWage 基本工资
     */
    void updateBasicWage(BasicWage basicWage);
}
