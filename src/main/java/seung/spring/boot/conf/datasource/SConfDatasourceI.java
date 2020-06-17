package seung.spring.boot.conf.datasource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.SProperties;

@Lazy
@EnableJpaRepositories(
    basePackages = {"seung.spring"}
    , entityManagerFactoryRef = "entityManagerFactoryI"
    , transactionManagerRef = "platformTransactionManagerI"
)
@EntityScan(basePackages = {"seung.spring"})
@EnableTransactionManagement
@Slf4j
@Configuration
public class SConfDatasourceI {

    @Primary
    @Bean(name = "dataSourceI", destroyMethod = "close")
    public DataSource dataSourceI(
            SProperties sProperties
            ) throws JsonProcessingException {
        log.debug("run");
        HikariConfig hikariConfig = new HikariConfig(sProperties.getDatasource().get(0));
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }
    
    @Primary
    @Bean(name = "sqlSessionFactoryI")
    public SqlSessionFactory sqlSessionFactoryI(
            @Qualifier("dataSourceI") DataSource dataSourceI
            ) throws Exception {
        
        log.debug("run");
        
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCallSettersOnNulls(true);
        configuration.setJdbcTypeForNull(JdbcType.VARCHAR);
        
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceI);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sql/dataSourceI/*.xml"));
        
        return sqlSessionFactoryBean.getObject();
    }
    
    @Primary
    @Bean(name = "sqlSessionTemplateI")
    public SqlSessionTemplate sqlSessionTemplateI(
            @Qualifier("sqlSessionFactoryI") SqlSessionFactory sqlSessionFactoryI
            ) {
        log.debug("run");
        return new SqlSessionTemplate(sqlSessionFactoryI);
    }
    
    @Primary
    @Bean(name = "platformTransactionManagerI")
    public PlatformTransactionManager platformTransactionManager(
            @Qualifier("entityManagerFactoryI") EntityManagerFactory entityManagerFactoryI
            ) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactoryI);
        return jpaTransactionManager;
    }
    
    @Bean(name = "jpaVendorAdapterI")
    public JpaVendorAdapter jpaVendorAdapterI(
            SProperties sProperties
            ) {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(sProperties.getJpaVendor().getProperty("spring.jpa.generate-ddl", "false")));
        hibernateJpaVendorAdapter.setShowSql(Boolean.parseBoolean(sProperties.getJpaVendor().getProperty("spring.jpa.show-sql", "false")));
//        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        return hibernateJpaVendorAdapter;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @Bean(name = "entityManagerFactoryI")
    public EntityManagerFactory entityManagerFactoryI(
            SProperties sProperties
            , @Qualifier("jpaVendorAdapterI") JpaVendorAdapter jpaVendorAdapterI
            , @Qualifier("dataSourceI") DataSource dataSourceI
            ) {
        
        log.debug("run");
        
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapterI);
        localContainerEntityManagerFactoryBean.setJpaProperties(sProperties.getJpa());
//        localContainerEntityManagerFactoryBean.getNativeEntityManagerFactory().
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("seung");
        localContainerEntityManagerFactoryBean.setDataSource(dataSourceI);
        localContainerEntityManagerFactoryBean.setPackagesToScan(new String[] {"seung.spring"});
        localContainerEntityManagerFactoryBean.afterPropertiesSet();
        
        return localContainerEntityManagerFactoryBean.getObject();
    }
    
}
