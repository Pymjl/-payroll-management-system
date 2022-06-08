package cuit.pymjl.core.service.finance;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.finance.BasicWage;
import cuit.pymjl.core.entity.finance.WageDetails;

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

    /**
     * 汇总本月的工资表
     * @return int
     */
    int summarySheet();

    /**
     * 获取本月所有员工的工资表
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return Page<WageDetails>
     */
    Page<WageDetails> getAllWageSheet(int pageNum, int pageSize);

    /**
     * 根据员工id查询该部门员工的工资表
     * @param id 员工id
     * @return WageDetails
     */
    WageDetails getWageSheetById(Long id);
}
