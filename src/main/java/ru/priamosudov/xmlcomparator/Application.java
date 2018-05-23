package ru.priamosudov.xmlcomparator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.xml.sax.SAXException;
import ru.priamosudov.xmlcomparator.directory.DirectoryLookup;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan
public class Application {
    private static final int MAX_XML_COMPARE_THREADS = 2;

    public static void main(String[] args) throws IOException, SAXException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        DirectoryLookup directoryLookup = context.getBean(DirectoryLookup.class);
        directoryLookup.lookup();
        directoryLookup.pushFilesToZipHandler();
    }

    @Bean(name = "zipArchiveServiceThreadPool")
    public ExecutorService ZipArchiveServiceThreadPool() {
        return Executors.newFixedThreadPool(MAX_XML_COMPARE_THREADS);
    }
}
