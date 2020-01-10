package cn.jianchen.com;

import cn.jianchen.com.trade.common.reposiotry.BaseRepositoryFactoryBean;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class SchoolTradeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolTradeApiApplication.class, args);
    }

    public static String[] getScanPackages() {
        return SchoolTradeApiApplication.class.getAnnotation(ComponentScan.class).value();
    }

    public static Reflections getAppReflection() {
        return new Reflections(new ConfigurationBuilder().forPackages(getScanPackages()));
    }

}
