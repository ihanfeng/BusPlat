package com.hg.msg.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by hg on 2016/4/12.
 */
@Configuration
public class DatasourceConfig {


//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource dataSource() {
//        return new DataSource();
//    }


//    @Bean
//    public DataSource dataSource(
//            @Value("${spring.datasource.driverClassName}") String driver,
//            @Value("${spring.datasource.url}") String url,
//            @Value("${spring.datasource.username}") String username,
//            @Value("${spring.datasource.password}") String password) {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setDriverClassName(driver);
//        druidDataSource.setUrl(url);
//        druidDataSource.setUsername(username);
//        druidDataSource.setPassword(password);
//        try {
//            druidDataSource.setFilters("stat, wall");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return druidDataSource;
//    }


//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/concretepage");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return dataSource;
//    }

}
