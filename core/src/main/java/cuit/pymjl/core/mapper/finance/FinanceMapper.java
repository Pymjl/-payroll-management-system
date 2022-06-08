package cuit.pymjl.core.mapper.finance;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.finance.BasicWage;
import cuit.pymjl.core.entity.finance.WageDetails;
import org.apache.ibatis.annotations.Param;


/**
 * @author Riou
 */

public interface FinanceMapper {

    /**
     * 获取基本工资
     * @return BasicWage
     */

    BasicWage queryBasicWage();

    /**
     * 更新基本工资
     * @param basicWage 基本工资
     * @return int
     */
    int updateBasicWage(@Param("basicWage") BasicWage basicWage);

    /**
     * 插入工资条
     * @param wageDetails 工资条
     * @return int
     */
    int addWageSheet(WageDetails wageDetails);

    /**
     * 获取所有的工资条
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return Page<WageDetails>
     */
    Page<WageDetails> queryAllWageSheet(@Param("pageNum") int pageNum,
                                        @Param("pageSize") int pageSize);

    /**
     * 根据用户id查询工资条
     * @param id 用户id
     * @return WageDetails
     */
    WageDetails queryWageSheetById(Long id);
}
