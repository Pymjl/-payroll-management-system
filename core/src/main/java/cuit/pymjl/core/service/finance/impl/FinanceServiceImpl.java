package cuit.pymjl.core.service.finance.impl;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.finance.BasicWage;
import cuit.pymjl.core.mapper.finance.FinanceMapper;
import cuit.pymjl.core.service.finance.FinanceService;
import cuit.pymjl.core.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Riou
 */
public class FinanceServiceImpl implements FinanceService {

    public FinanceServiceImpl() {}

    @Override
    public Page<BasicWage> queryBasicWage(Integer pageNum, Integer pageSize) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            FinanceMapper financeMapper = sqlSession.getMapper(FinanceMapper.class);
            return financeMapper.queryBasicWage(pageNum, pageSize);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }
}
