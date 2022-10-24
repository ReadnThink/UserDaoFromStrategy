package dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao LocalFactory(){
        return new UserDao(localdataSource());
    }
    @Bean
    public UserDao AwsFactory(){
        return new UserDao(awsdataSource());
    }

    @Bean
    public DataSource localdataSource(){
        Map<String, String> env = System.getenv();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/likelion-db");
        dataSource.setUrl(env.get("DB_USER"));
        dataSource.setUrl(env.get("DB_PASSWORD"));
        return dataSource;
    }
    @Bean
    public DataSource awsdataSource(){
        Map<String, String> env = System.getenv();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl(env.get("DB_HOST"));
        dataSource.setUrl(env.get("DB_USER"));
        dataSource.setUrl(env.get("DB_PASSWORD"));
        return dataSource;
    }
}
