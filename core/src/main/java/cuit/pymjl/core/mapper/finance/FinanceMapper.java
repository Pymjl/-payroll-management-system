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
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return {@code Page<BasicWage>}
     */
    Page<BasicWage> queryBasicWage(@Param("pageNum") int pageNum,
                                    @Param("pageSize") int  pageSize);
}
