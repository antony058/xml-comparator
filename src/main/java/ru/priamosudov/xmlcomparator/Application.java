package ru.priamosudov.xmlcomparator;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.priamosudov.xmlcomparator.directory.DirectoryLookup;

import javax.sql.DataSource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan
public class Application {
    private static final int MAX_XML_COMPARE_THREADS = 2;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        DirectoryLookup directoryLookup = context.getBean(DirectoryLookup.class);
        directoryLookup.lookup();
        directoryLookup.pushFilesToZipHandler();
        /*XmlComparator xmlComparator = context.getBean(XmlComparator.class);
        xmlComparator.compareXml();*/
    }

    @Bean(name = "zipArchiveServiceThreadPool")
    public ExecutorService ZipArchiveServiceThreadPool() {
        return Executors.newFixedThreadPool(MAX_XML_COMPARE_THREADS);
    }

    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("postgres");
        dataSource.setPassword("password");
        dataSource.setURL("jdbc:postgresql://localhost:5432/postgres");

        return dataSource;
    }
}
