package com.ela.blessing.star.web.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.ela.blessing.star.dao"}, sqlSessionFactoryRef = "sqlSessionFactoryEla")
public class DBConfig {

    @Bean(name = "ela")
    @ConfigurationProperties(prefix = "spring.datasource-ela") // application.properteis中对应属性的前缀
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactoryEla")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("ela") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.bitgame.exchange.lock.domain");
        //factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("com/ela/blessing/star/dao/sqlmapper/*.xml"));
        org.apache.ibatis.session.Configuration conf=new org.apache.ibatis.session.Configuration();
        conf.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(conf);
        return factoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplateELa")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryEla") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }
}