package cuit.pymjl.core.mapper.finance;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.finance.BasicWage;
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

}
