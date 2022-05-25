package cuit.pymjl.core.config;


import java.util.Properties;
import javax.sql.DataSource;

import cuit.pymjl.core.util.DruidUtils;
import org.apache.ibatis.datasource.DataSourceFactory;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/23 12:53
 **/
public class DruidDataSourceFactory implements DataSourceFactory {
    private Properties props;

    @Override
    public DataSource getDataSource() {
        DruidDataSource dds = new DruidDataSource();
        try {
            dds = (DruidDataSource) com.alibaba.druid.pool.DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dds;
    }

    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }
}
