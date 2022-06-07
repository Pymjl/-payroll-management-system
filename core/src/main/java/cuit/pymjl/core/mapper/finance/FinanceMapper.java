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

}
