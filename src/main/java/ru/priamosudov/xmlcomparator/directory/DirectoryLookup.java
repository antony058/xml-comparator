package ru.priamosudov.xmlcomparator.directory;

import org.springframework.stereotype.Component;
import ru.priamosudov.xmlcomparator.directory.validation.DirectoryValidator;
import ru.priamosudov.xmlcomparator.ziparchive.ZipArchiveHandler;

import java.io.File;
import java.io.FilenameFilter;

@Component
public class DirectoryLookup {
    private final ZipArchiveHandler zipHandler;
    private static final String DIRECTORY_PATH = "D://zip-files";
    private File[] files;

    public DirectoryLookup(ZipArchiveHandler zipHandler) {
        this.zipHandler = zipHandler;
    }

    public void lookup() {
        File file = new File(DIRECTORY_PATH);
        files = file.listFiles(getZipFilenameFilter());

        DirectoryValidator.checkFilesValid(files);
    }

    private FilenameFilter getZipFilenameFilter() {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".zip");
            }
        };
    }

    public void pushFilesToZipHandler() {
        for (File file: files) {
            zipHandler.addZipFile(file);
        }
    }
}
