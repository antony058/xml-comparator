package ru.priamosudov.xmlcomparator;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.priamosudov.xmlcomparator.directory.DirectoryLookup;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan
@EnableTransactionManagement
public class Application {
    private static final int MAX_XML_COMPARE_THREADS = 2;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        DirectoryLookup directoryLookup = context.getBean(DirectoryLookup.class);
        directoryLookup.lookup();
        directoryLookup.pushFilesToZipHandler();
    }

    @Bean(name = "zipArchiveServiceThreadPool")
    public ExecutorService ZipArchiveServiceThreadPool() {
        return Executors.newFixedThreadPool(MAX_XML_COMPARE_THREADS);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("ru.priamosudov.xmlcomparator");
        entityManager.setJpaProperties(properties());
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return entityManager;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("password");
        dataSource.setURL("jdbc:postgresql://localhost:5432/postgres");

        return dataSource;
    }

    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.setProperty("hibernate.show_sql", "true");

        return properties;
    }
}
