package cuit.pymjl.core.service.finance.impl;

import cuit.pymjl.core.entity.finance.BasicWage;
import cuit.pymjl.core.mapper.finance.FinanceMapper;
import cuit.pymjl.core.service.finance.FinanceService;
import cuit.pymjl.core.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;



/**
 * @author Riou
 */
public class FinanceServiceImpl implements FinanceService {

    public FinanceServiceImpl() {}

    @Override
    public BasicWage queryBasicWage() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            FinanceMapper financeMapper = sqlSession.getMapper(FinanceMapper.class);
            return financeMapper.queryBasicWage();
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }
}
