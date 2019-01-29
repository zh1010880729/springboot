package cn.linkedcare.config;

import cn.linkedcare.config.properties.DataSourceProperties;
import com.alibaba.druid.pool.DruidDataSource;
import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 多数据源实现读写分离
 * Created by Benji on 2018/5/8.
 */
@Configuration
public class DataSourceConfig {

    private static final Log log = LogFactory.getLog(DataSourceConfig.class);

    @Autowired
    private DataSourceProperties dataSourceProperties;
    @Autowired
    private Environment environment;

    private static final String URL = "spring.datasource.%s.url";
    private static final String USERNAME = "spring.datasource.%s.username";
    private static final String PASSWORD = "spring.datasource.%s.password";
    private static final String PRIVATEKEY = "spring.datasource.%s.privateKey";

//    /**
//     * 事物管理器
//     *
//     * @param dataSource
//     * @return
//     */
//    @Autowired
//    @Bean(name = "transactionManager")
//    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
//        return transactionManager;
//    }

    @Bean(name = "datasource")
    public DataSource dataSource() {
        DataSource masterDatasource = this.getDataSourceByName("master");
        DataSource slaveDatasource1 = this.getDataSourceByName("slave1");
        DataSource slaveDatasource2 = this.getDataSourceByName("slave2");
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("masterDatasource", masterDatasource);
        dataSourceMap.put("slaveDatasource1", slaveDatasource1);
        dataSourceMap.put("slaveDatasource2", slaveDatasource2);
        // 构建读写分离配置
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration();
        masterSlaveRuleConfig.setName("ms_ds");
        masterSlaveRuleConfig.setMasterDataSourceName("masterDatasource");
        masterSlaveRuleConfig.getSlaveDataSourceNames().add("slaveDatasource1");
        masterSlaveRuleConfig.getSlaveDataSourceNames().add("slaveDatasource2");
        DataSource dataSource = null;
        try {
            dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveRuleConfig, new HashMap<>());
        } catch (Exception e) {
            log.error("初始化连接池失败");
        }
        log.info("数据库连接池初始化成功");
        return dataSource;
    }

    /**
     * 获取分数据源
     *
     * @param name
     * @return
     */
    private DruidDataSource getDataSourceByName(String name) {
        DruidDataSource druidDataSource = new DruidDataSource();
        try {
            druidDataSource.setUrl(environment.getProperty(String.format(URL, name)));
            druidDataSource.setUsername(environment.getProperty(String.format(USERNAME, name)));
            druidDataSource.setPassword(environment.getProperty(String.format(PASSWORD, name)));
            druidDataSource.setFilters("config");
            Properties properties = new Properties();
            properties.setProperty("config.decrypt", String.valueOf(true));
            properties.setProperty("config.decrypt.key", environment.getProperty(String.format(PRIVATEKEY, name)));
            druidDataSource.setConnectProperties(properties);
            this.setDataSourceProperties(druidDataSource);
        } catch (SQLException e) {
            log.error("getDataSourceByName 出现异常", e);
        }
        return druidDataSource;
    }

    /**
     * 设置dataSource的属性
     *
     * @param druidDataSource
     */
    private void setDataSourceProperties(DruidDataSource druidDataSource) {
        druidDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        druidDataSource.setInitialSize(dataSourceProperties.getInitialSize());
        druidDataSource.setMinIdle(dataSourceProperties.getMinIdle());
        druidDataSource.setMaxActive(dataSourceProperties.getMaxActive());
        druidDataSource.setMaxWait(dataSourceProperties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(dataSourceProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(dataSourceProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(dataSourceProperties.getValidationQuery());
        druidDataSource.setTestWhileIdle(dataSourceProperties.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(dataSourceProperties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(dataSourceProperties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(dataSourceProperties.isPoolPreparedStatements());
    }
}
