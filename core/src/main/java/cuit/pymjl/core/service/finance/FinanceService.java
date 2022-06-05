package cuit.pymjl.core.service.finance;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.finance.BasicWage;

/**
 * @author Riou
 */
public interface FinanceService {
    /**
     * 获取基本工资列表
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return {@code Page<BasicWage>}
     */
    Page<BasicWage> queryBasicWage(Integer pageNum, Integer pageSize);
}
